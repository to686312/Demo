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

public class User_implRealmProxy extends com.example.shinelon.demo.demo7.User_impl
    implements RealmObjectProxy, User_implRealmProxyInterface {

    static final class User_implColumnInfo extends ColumnInfo
        implements Cloneable {

        public long nameIndex;
        public long ageIndex;

        User_implColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.nameIndex = getValidColumnIndex(path, table, "User_impl", "name");
            indicesMap.put("name", this.nameIndex);
            this.ageIndex = getValidColumnIndex(path, table, "User_impl", "age");
            indicesMap.put("age", this.ageIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final User_implColumnInfo otherInfo = (User_implColumnInfo) other;
            this.nameIndex = otherInfo.nameIndex;
            this.ageIndex = otherInfo.ageIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final User_implColumnInfo clone() {
            return (User_implColumnInfo) super.clone();
        }

    }
    private User_implColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("name");
        fieldNames.add("age");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    User_implRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (User_implColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.example.shinelon.demo.demo7.User_impl.class, this);
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
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
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
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.ageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.ageIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("User_impl")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("User_impl");
            realmObjectSchema.add(new Property("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("age", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("User_impl");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_User_impl")) {
            Table table = sharedRealm.getTable("class_User_impl");
            table.addColumn(RealmFieldType.STRING, "name", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "age", Table.NOT_NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_User_impl");
    }

    public static User_implColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_User_impl")) {
            Table table = sharedRealm.getTable("class_User_impl");
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

            final User_implColumnInfo columnInfo = new User_implColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("name")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("name") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("age")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'age' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("age") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'age' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.ageIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'age' does support null values in the existing Realm file. Use corresponding boxed type for field 'age' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'User_impl' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_User_impl";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.example.shinelon.demo.demo7.User_impl createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.shinelon.demo.demo7.User_impl obj = realm.createObjectInternal(com.example.shinelon.demo.demo7.User_impl.class, true, excludeFields);
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((User_implRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((User_implRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("age")) {
            if (json.isNull("age")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'age' to null.");
            } else {
                ((User_implRealmProxyInterface) obj).realmSet$age((int) json.getInt("age"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.shinelon.demo.demo7.User_impl createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.example.shinelon.demo.demo7.User_impl obj = new com.example.shinelon.demo.demo7.User_impl();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((User_implRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((User_implRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("age")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'age' to null.");
                } else {
                    ((User_implRealmProxyInterface) obj).realmSet$age((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.example.shinelon.demo.demo7.User_impl copyOrUpdate(Realm realm, com.example.shinelon.demo.demo7.User_impl object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.shinelon.demo.demo7.User_impl) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.example.shinelon.demo.demo7.User_impl copy(Realm realm, com.example.shinelon.demo.demo7.User_impl newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.shinelon.demo.demo7.User_impl) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.example.shinelon.demo.demo7.User_impl realmObject = realm.createObjectInternal(com.example.shinelon.demo.demo7.User_impl.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((User_implRealmProxyInterface) realmObject).realmSet$name(((User_implRealmProxyInterface) newObject).realmGet$name());
            ((User_implRealmProxyInterface) realmObject).realmSet$age(((User_implRealmProxyInterface) newObject).realmGet$age());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.example.shinelon.demo.demo7.User_impl object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User_impl.class);
        long tableNativePtr = table.getNativeTablePointer();
        User_implColumnInfo columnInfo = (User_implColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User_impl.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$name = ((User_implRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((User_implRealmProxyInterface)object).realmGet$age(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User_impl.class);
        long tableNativePtr = table.getNativeTablePointer();
        User_implColumnInfo columnInfo = (User_implColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User_impl.class);
        com.example.shinelon.demo.demo7.User_impl object = null;
        while (objects.hasNext()) {
            object = (com.example.shinelon.demo.demo7.User_impl) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$name = ((User_implRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((User_implRealmProxyInterface)object).realmGet$age(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.shinelon.demo.demo7.User_impl object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User_impl.class);
        long tableNativePtr = table.getNativeTablePointer();
        User_implColumnInfo columnInfo = (User_implColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User_impl.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$name = ((User_implRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((User_implRealmProxyInterface)object).realmGet$age(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.shinelon.demo.demo7.User_impl.class);
        long tableNativePtr = table.getNativeTablePointer();
        User_implColumnInfo columnInfo = (User_implColumnInfo) realm.schema.getColumnInfo(com.example.shinelon.demo.demo7.User_impl.class);
        com.example.shinelon.demo.demo7.User_impl object = null;
        while (objects.hasNext()) {
            object = (com.example.shinelon.demo.demo7.User_impl) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$name = ((User_implRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((User_implRealmProxyInterface)object).realmGet$age(), false);
            }
        }
    }

    public static com.example.shinelon.demo.demo7.User_impl createDetachedCopy(com.example.shinelon.demo.demo7.User_impl realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.shinelon.demo.demo7.User_impl unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.shinelon.demo.demo7.User_impl)cachedObject.object;
            } else {
                unmanagedObject = (com.example.shinelon.demo.demo7.User_impl)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.example.shinelon.demo.demo7.User_impl();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((User_implRealmProxyInterface) unmanagedObject).realmSet$name(((User_implRealmProxyInterface) realmObject).realmGet$name());
        ((User_implRealmProxyInterface) unmanagedObject).realmSet$age(((User_implRealmProxyInterface) realmObject).realmGet$age());
        return unmanagedObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("User_impl = [");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
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
        User_implRealmProxy aUser_impl = (User_implRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUser_impl.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUser_impl.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUser_impl.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
