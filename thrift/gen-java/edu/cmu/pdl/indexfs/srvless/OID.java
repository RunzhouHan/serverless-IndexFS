/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package edu.cmu.pdl.indexfs.srvless;

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
public class OID implements org.apache.thrift.TBase<OID, OID._Fields>, java.io.Serializable, Cloneable, Comparable<OID> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("OID");

  private static final org.apache.thrift.protocol.TField PATH_DEPTH_FIELD_DESC = new org.apache.thrift.protocol.TField("path_depth", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField DIR_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("dir_id", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField OBJ_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("obj_name", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new OIDStandardSchemeFactory());
    schemes.put(TupleScheme.class, new OIDTupleSchemeFactory());
  }

  public short path_depth; // required
  public long dir_id; // required
  public String obj_name; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PATH_DEPTH((short)1, "path_depth"),
    DIR_ID((short)2, "dir_id"),
    OBJ_NAME((short)3, "obj_name");

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
        case 1: // PATH_DEPTH
          return PATH_DEPTH;
        case 2: // DIR_ID
          return DIR_ID;
        case 3: // OBJ_NAME
          return OBJ_NAME;
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
  private static final int __PATH_DEPTH_ISSET_ID = 0;
  private static final int __DIR_ID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PATH_DEPTH, new org.apache.thrift.meta_data.FieldMetaData("path_depth", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.DIR_ID, new org.apache.thrift.meta_data.FieldMetaData("dir_id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.OBJ_NAME, new org.apache.thrift.meta_data.FieldMetaData("obj_name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(OID.class, metaDataMap);
  }

  public OID() {
  }

  public OID(
    short path_depth,
    long dir_id,
    String obj_name)
  {
    this();
    this.path_depth = path_depth;
    setPath_depthIsSet(true);
    this.dir_id = dir_id;
    setDir_idIsSet(true);
    this.obj_name = obj_name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public OID(OID other) {
    __isset_bitfield = other.__isset_bitfield;
    this.path_depth = other.path_depth;
    this.dir_id = other.dir_id;
    if (other.isSetObj_name()) {
      this.obj_name = other.obj_name;
    }
  }

  public OID deepCopy() {
    return new OID(this);
  }

  @Override
  public void clear() {
    setPath_depthIsSet(false);
    this.path_depth = 0;
    setDir_idIsSet(false);
    this.dir_id = 0;
    this.obj_name = null;
  }

  public short getPath_depth() {
    return this.path_depth;
  }

  public OID setPath_depth(short path_depth) {
    this.path_depth = path_depth;
    setPath_depthIsSet(true);
    return this;
  }

  public void unsetPath_depth() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PATH_DEPTH_ISSET_ID);
  }

  /** Returns true if field path_depth is set (has been assigned a value) and false otherwise */
  public boolean isSetPath_depth() {
    return EncodingUtils.testBit(__isset_bitfield, __PATH_DEPTH_ISSET_ID);
  }

  public void setPath_depthIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PATH_DEPTH_ISSET_ID, value);
  }

  public long getDir_id() {
    return this.dir_id;
  }

  public OID setDir_id(long dir_id) {
    this.dir_id = dir_id;
    setDir_idIsSet(true);
    return this;
  }

  public void unsetDir_id() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DIR_ID_ISSET_ID);
  }

  /** Returns true if field dir_id is set (has been assigned a value) and false otherwise */
  public boolean isSetDir_id() {
    return EncodingUtils.testBit(__isset_bitfield, __DIR_ID_ISSET_ID);
  }

  public void setDir_idIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DIR_ID_ISSET_ID, value);
  }

  public String getObj_name() {
    return this.obj_name;
  }

  public OID setObj_name(String obj_name) {
    this.obj_name = obj_name;
    return this;
  }

  public void unsetObj_name() {
    this.obj_name = null;
  }

  /** Returns true if field obj_name is set (has been assigned a value) and false otherwise */
  public boolean isSetObj_name() {
    return this.obj_name != null;
  }

  public void setObj_nameIsSet(boolean value) {
    if (!value) {
      this.obj_name = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PATH_DEPTH:
      if (value == null) {
        unsetPath_depth();
      } else {
        setPath_depth((Short)value);
      }
      break;

    case DIR_ID:
      if (value == null) {
        unsetDir_id();
      } else {
        setDir_id((Long)value);
      }
      break;

    case OBJ_NAME:
      if (value == null) {
        unsetObj_name();
      } else {
        setObj_name((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PATH_DEPTH:
      return getPath_depth();

    case DIR_ID:
      return getDir_id();

    case OBJ_NAME:
      return getObj_name();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PATH_DEPTH:
      return isSetPath_depth();
    case DIR_ID:
      return isSetDir_id();
    case OBJ_NAME:
      return isSetObj_name();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof OID)
      return this.equals((OID)that);
    return false;
  }

  public boolean equals(OID that) {
    if (that == null)
      return false;

    boolean this_present_path_depth = true;
    boolean that_present_path_depth = true;
    if (this_present_path_depth || that_present_path_depth) {
      if (!(this_present_path_depth && that_present_path_depth))
        return false;
      if (this.path_depth != that.path_depth)
        return false;
    }

    boolean this_present_dir_id = true;
    boolean that_present_dir_id = true;
    if (this_present_dir_id || that_present_dir_id) {
      if (!(this_present_dir_id && that_present_dir_id))
        return false;
      if (this.dir_id != that.dir_id)
        return false;
    }

    boolean this_present_obj_name = true && this.isSetObj_name();
    boolean that_present_obj_name = true && that.isSetObj_name();
    if (this_present_obj_name || that_present_obj_name) {
      if (!(this_present_obj_name && that_present_obj_name))
        return false;
      if (!this.obj_name.equals(that.obj_name))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_path_depth = true;
    list.add(present_path_depth);
    if (present_path_depth)
      list.add(path_depth);

    boolean present_dir_id = true;
    list.add(present_dir_id);
    if (present_dir_id)
      list.add(dir_id);

    boolean present_obj_name = true && (isSetObj_name());
    list.add(present_obj_name);
    if (present_obj_name)
      list.add(obj_name);

    return list.hashCode();
  }

  @Override
  public int compareTo(OID other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPath_depth()).compareTo(other.isSetPath_depth());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPath_depth()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.path_depth, other.path_depth);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDir_id()).compareTo(other.isSetDir_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDir_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dir_id, other.dir_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetObj_name()).compareTo(other.isSetObj_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetObj_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.obj_name, other.obj_name);
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
    StringBuilder sb = new StringBuilder("OID(");
    boolean first = true;

    sb.append("path_depth:");
    sb.append(this.path_depth);
    first = false;
    if (!first) sb.append(", ");
    sb.append("dir_id:");
    sb.append(this.dir_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("obj_name:");
    if (this.obj_name == null) {
      sb.append("null");
    } else {
      sb.append(this.obj_name);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'path_depth' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'dir_id' because it's a primitive and you chose the non-beans generator.
    if (obj_name == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'obj_name' was not present! Struct: " + toString());
    }
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

  private static class OIDStandardSchemeFactory implements SchemeFactory {
    public OIDStandardScheme getScheme() {
      return new OIDStandardScheme();
    }
  }

  private static class OIDStandardScheme extends StandardScheme<OID> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, OID struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PATH_DEPTH
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.path_depth = iprot.readI16();
              struct.setPath_depthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DIR_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.dir_id = iprot.readI64();
              struct.setDir_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // OBJ_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.obj_name = iprot.readString();
              struct.setObj_nameIsSet(true);
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
      if (!struct.isSetPath_depth()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'path_depth' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetDir_id()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'dir_id' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, OID struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(PATH_DEPTH_FIELD_DESC);
      oprot.writeI16(struct.path_depth);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(DIR_ID_FIELD_DESC);
      oprot.writeI64(struct.dir_id);
      oprot.writeFieldEnd();
      if (struct.obj_name != null) {
        oprot.writeFieldBegin(OBJ_NAME_FIELD_DESC);
        oprot.writeString(struct.obj_name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class OIDTupleSchemeFactory implements SchemeFactory {
    public OIDTupleScheme getScheme() {
      return new OIDTupleScheme();
    }
  }

  private static class OIDTupleScheme extends TupleScheme<OID> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, OID struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI16(struct.path_depth);
      oprot.writeI64(struct.dir_id);
      oprot.writeString(struct.obj_name);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, OID struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.path_depth = iprot.readI16();
      struct.setPath_depthIsSet(true);
      struct.dir_id = iprot.readI64();
      struct.setDir_idIsSet(true);
      struct.obj_name = iprot.readString();
      struct.setObj_nameIsSet(true);
    }
  }

}

