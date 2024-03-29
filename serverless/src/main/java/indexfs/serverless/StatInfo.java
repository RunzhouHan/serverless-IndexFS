/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package main.java.indexfs.serverless;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2021-12-06")
public class StatInfo implements org.apache.thrift.TBase<StatInfo, StatInfo._Fields>, java.io.Serializable, Cloneable, Comparable<StatInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("StatInfo");

  private static final org.apache.thrift.protocol.TField MODE_FIELD_DESC = new org.apache.thrift.protocol.TField("mode", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField UID_FIELD_DESC = new org.apache.thrift.protocol.TField("uid", org.apache.thrift.protocol.TType.I16, (short)2);
  private static final org.apache.thrift.protocol.TField GID_FIELD_DESC = new org.apache.thrift.protocol.TField("gid", org.apache.thrift.protocol.TType.I16, (short)3);
  private static final org.apache.thrift.protocol.TField SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("size", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField MTIME_FIELD_DESC = new org.apache.thrift.protocol.TField("mtime", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField CTIME_FIELD_DESC = new org.apache.thrift.protocol.TField("ctime", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)7);
  private static final org.apache.thrift.protocol.TField ZEROTH_SERVER_FIELD_DESC = new org.apache.thrift.protocol.TField("zeroth_server", org.apache.thrift.protocol.TType.I16, (short)8);
  private static final org.apache.thrift.protocol.TField IS_EMBEDDED_FIELD_DESC = new org.apache.thrift.protocol.TField("is_embedded", org.apache.thrift.protocol.TType.BOOL, (short)9);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new StatInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new StatInfoTupleSchemeFactory());
  }

  public int mode; // required
  public short uid; // required
  public short gid; // required
  public long size; // required
  public long mtime; // required
  public long ctime; // required
  public long id; // required
  public short zeroth_server; // required
  public boolean is_embedded; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MODE((short)1, "mode"),
    UID((short)2, "uid"),
    GID((short)3, "gid"),
    SIZE((short)4, "size"),
    MTIME((short)5, "mtime"),
    CTIME((short)6, "ctime"),
    ID((short)7, "id"),
    ZEROTH_SERVER((short)8, "zeroth_server"),
    IS_EMBEDDED((short)9, "is_embedded");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MODE
          return MODE;
        case 2: // UID
          return UID;
        case 3: // GID
          return GID;
        case 4: // SIZE
          return SIZE;
        case 5: // MTIME
          return MTIME;
        case 6: // CTIME
          return CTIME;
        case 7: // ID
          return ID;
        case 8: // ZEROTH_SERVER
          return ZEROTH_SERVER;
        case 9: // IS_EMBEDDED
          return IS_EMBEDDED;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __MODE_ISSET_ID = 0;
  private static final int __UID_ISSET_ID = 1;
  private static final int __GID_ISSET_ID = 2;
  private static final int __SIZE_ISSET_ID = 3;
  private static final int __MTIME_ISSET_ID = 4;
  private static final int __CTIME_ISSET_ID = 5;
  private static final int __ID_ISSET_ID = 6;
  private static final int __ZEROTH_SERVER_ISSET_ID = 7;
  private static final int __IS_EMBEDDED_ISSET_ID = 8;
  private short __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MODE, new org.apache.thrift.meta_data.FieldMetaData("mode", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.UID, new org.apache.thrift.meta_data.FieldMetaData("uid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.GID, new org.apache.thrift.meta_data.FieldMetaData("gid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.SIZE, new org.apache.thrift.meta_data.FieldMetaData("size", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MTIME, new org.apache.thrift.meta_data.FieldMetaData("mtime", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.CTIME, new org.apache.thrift.meta_data.FieldMetaData("ctime", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ZEROTH_SERVER, new org.apache.thrift.meta_data.FieldMetaData("zeroth_server", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.IS_EMBEDDED, new org.apache.thrift.meta_data.FieldMetaData("is_embedded", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(StatInfo.class, metaDataMap);
  }

  public StatInfo() {
  }

  public StatInfo(
    int mode,
    short uid,
    short gid,
    long size,
    long mtime,
    long ctime,
    long id,
    short zeroth_server,
    boolean is_embedded)
  {
    this();
    this.mode = mode;
    setModeIsSet(true);
    this.uid = uid;
    setUidIsSet(true);
    this.gid = gid;
    setGidIsSet(true);
    this.size = size;
    setSizeIsSet(true);
    this.mtime = mtime;
    setMtimeIsSet(true);
    this.ctime = ctime;
    setCtimeIsSet(true);
    this.id = id;
    setIdIsSet(true);
    this.zeroth_server = zeroth_server;
    setZeroth_serverIsSet(true);
    this.is_embedded = is_embedded;
    setIs_embeddedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public StatInfo(StatInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.mode = other.mode;
    this.uid = other.uid;
    this.gid = other.gid;
    this.size = other.size;
    this.mtime = other.mtime;
    this.ctime = other.ctime;
    this.id = other.id;
    this.zeroth_server = other.zeroth_server;
    this.is_embedded = other.is_embedded;
  }

  public StatInfo deepCopy() {
    return new StatInfo(this);
  }

  @Override
  public void clear() {
    setModeIsSet(false);
    this.mode = 0;
    setUidIsSet(false);
    this.uid = 0;
    setGidIsSet(false);
    this.gid = 0;
    setSizeIsSet(false);
    this.size = 0;
    setMtimeIsSet(false);
    this.mtime = 0;
    setCtimeIsSet(false);
    this.ctime = 0;
    setIdIsSet(false);
    this.id = 0;
    setZeroth_serverIsSet(false);
    this.zeroth_server = 0;
    setIs_embeddedIsSet(false);
    this.is_embedded = false;
  }

  public int getMode() {
    return this.mode;
  }

  public StatInfo setMode(int mode) {
    this.mode = mode;
    setModeIsSet(true);
    return this;
  }

  public void unsetMode() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MODE_ISSET_ID);
  }

  /** Returns true if field mode is set (has been assigned a value) and false otherwise */
  public boolean isSetMode() {
    return EncodingUtils.testBit(__isset_bitfield, __MODE_ISSET_ID);
  }

  public void setModeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MODE_ISSET_ID, value);
  }

  public short getUid() {
    return this.uid;
  }

  public StatInfo setUid(short uid) {
    this.uid = uid;
    setUidIsSet(true);
    return this;
  }

  public void unsetUid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __UID_ISSET_ID);
  }

  /** Returns true if field uid is set (has been assigned a value) and false otherwise */
  public boolean isSetUid() {
    return EncodingUtils.testBit(__isset_bitfield, __UID_ISSET_ID);
  }

  public void setUidIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __UID_ISSET_ID, value);
  }

  public short getGid() {
    return this.gid;
  }

  public StatInfo setGid(short gid) {
    this.gid = gid;
    setGidIsSet(true);
    return this;
  }

  public void unsetGid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __GID_ISSET_ID);
  }

  /** Returns true if field gid is set (has been assigned a value) and false otherwise */
  public boolean isSetGid() {
    return EncodingUtils.testBit(__isset_bitfield, __GID_ISSET_ID);
  }

  public void setGidIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __GID_ISSET_ID, value);
  }

  public long getSize() {
    return this.size;
  }

  public StatInfo setSize(long size) {
    this.size = size;
    setSizeIsSet(true);
    return this;
  }

  public void unsetSize() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  /** Returns true if field size is set (has been assigned a value) and false otherwise */
  public boolean isSetSize() {
    return EncodingUtils.testBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  public void setSizeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SIZE_ISSET_ID, value);
  }

  public long getMtime() {
    return this.mtime;
  }

  public StatInfo setMtime(long mtime) {
    this.mtime = mtime;
    setMtimeIsSet(true);
    return this;
  }

  public void unsetMtime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MTIME_ISSET_ID);
  }

  /** Returns true if field mtime is set (has been assigned a value) and false otherwise */
  public boolean isSetMtime() {
    return EncodingUtils.testBit(__isset_bitfield, __MTIME_ISSET_ID);
  }

  public void setMtimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MTIME_ISSET_ID, value);
  }

  public long getCtime() {
    return this.ctime;
  }

  public StatInfo setCtime(long ctime) {
    this.ctime = ctime;
    setCtimeIsSet(true);
    return this;
  }

  public void unsetCtime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CTIME_ISSET_ID);
  }

  /** Returns true if field ctime is set (has been assigned a value) and false otherwise */
  public boolean isSetCtime() {
    return EncodingUtils.testBit(__isset_bitfield, __CTIME_ISSET_ID);
  }

  public void setCtimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CTIME_ISSET_ID, value);
  }

  public long getId() {
    return this.id;
  }

  public StatInfo setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public short getZeroth_server() {
    return this.zeroth_server;
  }

  public StatInfo setZeroth_server(short zeroth_server) {
    this.zeroth_server = zeroth_server;
    setZeroth_serverIsSet(true);
    return this;
  }

  public void unsetZeroth_server() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ZEROTH_SERVER_ISSET_ID);
  }

  /** Returns true if field zeroth_server is set (has been assigned a value) and false otherwise */
  public boolean isSetZeroth_server() {
    return EncodingUtils.testBit(__isset_bitfield, __ZEROTH_SERVER_ISSET_ID);
  }

  public void setZeroth_serverIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ZEROTH_SERVER_ISSET_ID, value);
  }

  public boolean isIs_embedded() {
    return this.is_embedded;
  }

  public StatInfo setIs_embedded(boolean is_embedded) {
    this.is_embedded = is_embedded;
    setIs_embeddedIsSet(true);
    return this;
  }

  public void unsetIs_embedded() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __IS_EMBEDDED_ISSET_ID);
  }

  /** Returns true if field is_embedded is set (has been assigned a value) and false otherwise */
  public boolean isSetIs_embedded() {
    return EncodingUtils.testBit(__isset_bitfield, __IS_EMBEDDED_ISSET_ID);
  }

  public void setIs_embeddedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __IS_EMBEDDED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MODE:
      if (value == null) {
        unsetMode();
      } else {
        setMode((Integer)value);
      }
      break;

    case UID:
      if (value == null) {
        unsetUid();
      } else {
        setUid((Short)value);
      }
      break;

    case GID:
      if (value == null) {
        unsetGid();
      } else {
        setGid((Short)value);
      }
      break;

    case SIZE:
      if (value == null) {
        unsetSize();
      } else {
        setSize((Long)value);
      }
      break;

    case MTIME:
      if (value == null) {
        unsetMtime();
      } else {
        setMtime((Long)value);
      }
      break;

    case CTIME:
      if (value == null) {
        unsetCtime();
      } else {
        setCtime((Long)value);
      }
      break;

    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case ZEROTH_SERVER:
      if (value == null) {
        unsetZeroth_server();
      } else {
        setZeroth_server((Short)value);
      }
      break;

    case IS_EMBEDDED:
      if (value == null) {
        unsetIs_embedded();
      } else {
        setIs_embedded((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MODE:
      return getMode();

    case UID:
      return getUid();

    case GID:
      return getGid();

    case SIZE:
      return getSize();

    case MTIME:
      return getMtime();

    case CTIME:
      return getCtime();

    case ID:
      return getId();

    case ZEROTH_SERVER:
      return getZeroth_server();

    case IS_EMBEDDED:
      return isIs_embedded();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MODE:
      return isSetMode();
    case UID:
      return isSetUid();
    case GID:
      return isSetGid();
    case SIZE:
      return isSetSize();
    case MTIME:
      return isSetMtime();
    case CTIME:
      return isSetCtime();
    case ID:
      return isSetId();
    case ZEROTH_SERVER:
      return isSetZeroth_server();
    case IS_EMBEDDED:
      return isSetIs_embedded();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof StatInfo)
      return this.equals((StatInfo)that);
    return false;
  }

  public boolean equals(StatInfo that) {
    if (that == null)
      return false;

    boolean this_present_mode = true;
    boolean that_present_mode = true;
    if (this_present_mode || that_present_mode) {
      if (!(this_present_mode && that_present_mode))
        return false;
      if (this.mode != that.mode)
        return false;
    }

    boolean this_present_uid = true;
    boolean that_present_uid = true;
    if (this_present_uid || that_present_uid) {
      if (!(this_present_uid && that_present_uid))
        return false;
      if (this.uid != that.uid)
        return false;
    }

    boolean this_present_gid = true;
    boolean that_present_gid = true;
    if (this_present_gid || that_present_gid) {
      if (!(this_present_gid && that_present_gid))
        return false;
      if (this.gid != that.gid)
        return false;
    }

    boolean this_present_size = true;
    boolean that_present_size = true;
    if (this_present_size || that_present_size) {
      if (!(this_present_size && that_present_size))
        return false;
      if (this.size != that.size)
        return false;
    }

    boolean this_present_mtime = true;
    boolean that_present_mtime = true;
    if (this_present_mtime || that_present_mtime) {
      if (!(this_present_mtime && that_present_mtime))
        return false;
      if (this.mtime != that.mtime)
        return false;
    }

    boolean this_present_ctime = true;
    boolean that_present_ctime = true;
    if (this_present_ctime || that_present_ctime) {
      if (!(this_present_ctime && that_present_ctime))
        return false;
      if (this.ctime != that.ctime)
        return false;
    }

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_zeroth_server = true;
    boolean that_present_zeroth_server = true;
    if (this_present_zeroth_server || that_present_zeroth_server) {
      if (!(this_present_zeroth_server && that_present_zeroth_server))
        return false;
      if (this.zeroth_server != that.zeroth_server)
        return false;
    }

    boolean this_present_is_embedded = true;
    boolean that_present_is_embedded = true;
    if (this_present_is_embedded || that_present_is_embedded) {
      if (!(this_present_is_embedded && that_present_is_embedded))
        return false;
      if (this.is_embedded != that.is_embedded)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_mode = true;
    list.add(present_mode);
    if (present_mode)
      list.add(mode);

    boolean present_uid = true;
    list.add(present_uid);
    if (present_uid)
      list.add(uid);

    boolean present_gid = true;
    list.add(present_gid);
    if (present_gid)
      list.add(gid);

    boolean present_size = true;
    list.add(present_size);
    if (present_size)
      list.add(size);

    boolean present_mtime = true;
    list.add(present_mtime);
    if (present_mtime)
      list.add(mtime);

    boolean present_ctime = true;
    list.add(present_ctime);
    if (present_ctime)
      list.add(ctime);

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_zeroth_server = true;
    list.add(present_zeroth_server);
    if (present_zeroth_server)
      list.add(zeroth_server);

    boolean present_is_embedded = true;
    list.add(present_is_embedded);
    if (present_is_embedded)
      list.add(is_embedded);

    return list.hashCode();
  }

  @Override
  public int compareTo(StatInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetMode()).compareTo(other.isSetMode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mode, other.mode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUid()).compareTo(other.isSetUid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uid, other.uid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGid()).compareTo(other.isSetGid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.gid, other.gid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSize()).compareTo(other.isSetSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.size, other.size);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMtime()).compareTo(other.isSetMtime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMtime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mtime, other.mtime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCtime()).compareTo(other.isSetCtime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctime, other.ctime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetZeroth_server()).compareTo(other.isSetZeroth_server());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetZeroth_server()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.zeroth_server, other.zeroth_server);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIs_embedded()).compareTo(other.isSetIs_embedded());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIs_embedded()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.is_embedded, other.is_embedded);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("StatInfo(");
    boolean first = true;

    sb.append("mode:");
    sb.append(this.mode);
    first = false;
    if (!first) sb.append(", ");
    sb.append("uid:");
    sb.append(this.uid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("gid:");
    sb.append(this.gid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("size:");
    sb.append(this.size);
    first = false;
    if (!first) sb.append(", ");
    sb.append("mtime:");
    sb.append(this.mtime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("ctime:");
    sb.append(this.ctime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("zeroth_server:");
    sb.append(this.zeroth_server);
    first = false;
    if (!first) sb.append(", ");
    sb.append("is_embedded:");
    sb.append(this.is_embedded);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'mode' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'uid' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'gid' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'size' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'mtime' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'ctime' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'id' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'zeroth_server' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'is_embedded' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class StatInfoStandardSchemeFactory implements SchemeFactory {
    public StatInfoStandardScheme getScheme() {
      return new StatInfoStandardScheme();
    }
  }

  private static class StatInfoStandardScheme extends StandardScheme<StatInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, StatInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.mode = iprot.readI32();
              struct.setModeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // UID
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.uid = iprot.readI16();
              struct.setUidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // GID
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.gid = iprot.readI16();
              struct.setGidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.size = iprot.readI64();
              struct.setSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // MTIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.mtime = iprot.readI64();
              struct.setMtimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CTIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.ctime = iprot.readI64();
              struct.setCtimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // ZEROTH_SERVER
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.zeroth_server = iprot.readI16();
              struct.setZeroth_serverIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // IS_EMBEDDED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.is_embedded = iprot.readBool();
              struct.setIs_embeddedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetMode()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'mode' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetUid()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'uid' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetGid()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'gid' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetSize()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'size' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetMtime()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'mtime' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetCtime()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'ctime' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetZeroth_server()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'zeroth_server' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetIs_embedded()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'is_embedded' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, StatInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(MODE_FIELD_DESC);
      oprot.writeI32(struct.mode);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(UID_FIELD_DESC);
      oprot.writeI16(struct.uid);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(GID_FIELD_DESC);
      oprot.writeI16(struct.gid);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SIZE_FIELD_DESC);
      oprot.writeI64(struct.size);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MTIME_FIELD_DESC);
      oprot.writeI64(struct.mtime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CTIME_FIELD_DESC);
      oprot.writeI64(struct.ctime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ZEROTH_SERVER_FIELD_DESC);
      oprot.writeI16(struct.zeroth_server);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(IS_EMBEDDED_FIELD_DESC);
      oprot.writeBool(struct.is_embedded);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class StatInfoTupleSchemeFactory implements SchemeFactory {
    public StatInfoTupleScheme getScheme() {
      return new StatInfoTupleScheme();
    }
  }

  private static class StatInfoTupleScheme extends TupleScheme<StatInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, StatInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.mode);
      oprot.writeI16(struct.uid);
      oprot.writeI16(struct.gid);
      oprot.writeI64(struct.size);
      oprot.writeI64(struct.mtime);
      oprot.writeI64(struct.ctime);
      oprot.writeI64(struct.id);
      oprot.writeI16(struct.zeroth_server);
      oprot.writeBool(struct.is_embedded);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, StatInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.mode = iprot.readI32();
      struct.setModeIsSet(true);
      struct.uid = iprot.readI16();
      struct.setUidIsSet(true);
      struct.gid = iprot.readI16();
      struct.setGidIsSet(true);
      struct.size = iprot.readI64();
      struct.setSizeIsSet(true);
      struct.mtime = iprot.readI64();
      struct.setMtimeIsSet(true);
      struct.ctime = iprot.readI64();
      struct.setCtimeIsSet(true);
      struct.id = iprot.readI64();
      struct.setIdIsSet(true);
      struct.zeroth_server = iprot.readI16();
      struct.setZeroth_serverIsSet(true);
      struct.is_embedded = iprot.readBool();
      struct.setIs_embeddedIsSet(true);
    }
  }

}

