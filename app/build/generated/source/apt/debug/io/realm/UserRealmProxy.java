package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserRealmProxy extends com.example.shinelon.demo.demo7.User
    implements RealmObjectProxy, UserRealmProxyInterface {

    static final class UserColumnInfo extends ColumnInfo
        implements Cloneable {

        public long nameIndex;
        public long ageIndex;

        UserColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.nameIndex = getValidColumnIndex(path, table, "User", "name");
            indicesMap.put("name", this.nameIndex);
            this.ageIndex = getValidColumnIndex(path, table, "User", "age");
            indicesMap.put("age", this.ageIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final UserColumnInfo otherInfo = (UserColumnInfo) other;
            this.nameIndex = otherInfo.nameIndex;
            this.ageIndex = otherInfo.ageIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final UserColumnInfo clone() {
            return (UserColumnInfo) super.clone();
        }

    }
    private UserColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("name");
        fieldNames.add("age");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    UserRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.example.shinelon.demo.demo7.User.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$name() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    public void realmSet$name(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'name' to null.");
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'name' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @SuppressWarnings("cast")
    public int realmGet$age() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.ageIndex);
    }

    public void realmSet$age(int value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'age' cannot be changed after object was created.");
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("User")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("User");
            realmObjectSchema.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            realmObjectSchema.add(new Property("age", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("User");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_User")) {
            Table table = sharedRealm.getTable("class_User");
            table.addColumn(RealmFieldType.STRING, "name", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "age", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("age"));
            table.setPrimaryKey("age");
            return table;
        }
        return sharedRealm.getTable("class_User");
    }

    public static UserColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_User")) {
            Table table = sharedRealm.getTable("class_User");
            final long columnCount = table.getColumnCount();
            if (columnCount != 2) {
                if (columnCount < 2) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < columnCount; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final UserColumnInfo columnInfo = new UserColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("age")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'age' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("age") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'age' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.ageIndex) && table.findFirstNull(columnInfo.ageIndex) != TableOrView.NO_MATCH) {
                throw new IllegalStateException("Cannot migrate an object with null value in field 'age'. Either maintain the same type for primary key field 'age', or remove the object with null value before migration.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("age")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'age' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("age"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'age' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'User' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_User";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.shinelon.demo.demo7.User createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.shinelon.demo.demo7.User obj = null;
        if (update) {
            Table table = realm.getTable(com.example.shinelon.demo.demo7.User.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (!json.isNull("age")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("age"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User.class), false, Collections.<String> emptyList());
                    obj = new io.realm.UserRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("age")) {
                if (json.isNull("age")) {
                    obj = (io.realm.UserRealmProxy) realm.createObjectInternal(com.example.shinelon.demo.demo7.User.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.UserRealmProxy) realm.createObjectInternal(com.example.shinelon.demo.demo7.User.class, json.getInt("age"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'age'.");
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((UserRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((UserRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.shinelon.demo.demo7.User createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.example.shinelon.demo.demo7.User obj = new com.example.shinelon.demo.demo7.User();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((UserRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("age")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'age' to null.");
                } else {
                    ((UserRealmProxyInterface) obj).realmSet$age((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'age'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.example.shinelon.demo.demo7.User copyOrUpdate(Realm realm, com.example.shinelon.demo.demo7.User object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.shinelon.demo.demo7.User) cachedRealmObject;
        } else {
            com.example.shinelon.demo.demo7.User realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.example.shinelon.demo.demo7.User.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstLong(pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$age());
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.UserRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.example.shinelon.demo.demo7.User copy(Realm realm, com.example.shinelon.demo.demo7.User newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.shinelon.demo.demo7.User) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.example.shinelon.demo.demo7.User realmObject = realm.createObjectInternal(com.example.shinelon.demo.demo7.User.class, ((UserRealmProxyInterface) newObject).realmGet$age(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((UserRealmProxyInterface) realmObject).realmSet$name(((UserRealmProxyInterface) newObject).realmGet$name());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.example.shinelon.demo.demo7.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$age();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$age());
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((UserRealmProxyInterface) object).realmGet$age(), false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((UserRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.example.shinelon.demo.demo7.User object = null;
        while (objects.hasNext()) {
            object = (com.example.shinelon.demo.demo7.User) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$age();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$age());
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((UserRealmProxyInterface) object).realmGet$age(), false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((UserRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.shinelon.demo.demo7.User object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = TableOrView.NO_MATCH;
        Object primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$age();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$age());
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(((UserRealmProxyInterface) object).realmGet$age(), false);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((UserRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User.class);
        long tableNativePtr = table.getNativeTablePointer();
        UserColumnInfo columnInfo = (UserColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.example.shinelon.demo.demo7.User object = null;
        while (objects.hasNext()) {
            object = (com.example.shinelon.demo.demo7.User) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = TableOrView.NO_MATCH;
                Object primaryKeyValue = ((UserRealmProxyInterface) object).realmGet$age();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((UserRealmProxyInterface) object).realmGet$age());
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(((UserRealmProxyInterface) object).realmGet$age(), false);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((UserRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.example.shinelon.demo.demo7.User createDetachedCopy(com.example.shinelon.demo.demo7.User realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.shinelon.demo.demo7.User unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.shinelon.demo.demo7.User)cachedObject.object;
            } else {
                unmanagedObject = (com.example.shinelon.demo.demo7.User)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.example.shinelon.demo.demo7.User();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((UserRealmProxyInterface) unmanagedObject).realmSet$name(((UserRealmProxyInterface) realmObject).realmGet$name());
        ((UserRealmProxyInterface) unmanagedObject).realmSet$age(((UserRealmProxyInterface) realmObject).realmGet$age());
        return unmanagedObject;
    }

    static com.example.shinelon.demo.demo7.User update(Realm realm, com.example.shinelon.demo.demo7.User realmObject, com.example.shinelon.demo.demo7.User newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((UserRealmProxyInterface) realmObject).realmSet$name(((UserRealmProxyInterface) newObject).realmGet$name());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("User = [");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{age:");
        stringBuilder.append(realmGet$age());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRealmProxy aUser = (UserRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUser.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUser.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUser.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
