/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package edu.cmu.pdl.indexfs.rpc;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2021-05-04")
public class ParentPathNotFoundException extends TException implements org.apache.thrift.TBase<ParentPathNotFoundException, ParentPathNotFoundException._Fields>, java.io.Serializable, Cloneable, Comparable<ParentPathNotFoundException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ParentPathNotFoundException");

  private static final org.apache.thrift.protocol.TField PARENT_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("parent_path", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ParentPathNotFoundExceptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ParentPathNotFoundExceptionTupleSchemeFactory());
  }

  public String parent_path; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PARENT_PATH((short)1, "parent_path");

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
        case 1: // PARENT_PATH
          return PARENT_PATH;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PARENT_PATH, new org.apache.thrift.meta_data.FieldMetaData("parent_path", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ParentPathNotFoundException.class, metaDataMap);
  }

  public ParentPathNotFoundException() {
  }

  public ParentPathNotFoundException(
    String parent_path)
  {
    this();
    this.parent_path = parent_path;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ParentPathNotFoundException(ParentPathNotFoundException other) {
    if (other.isSetParent_path()) {
      this.parent_path = other.parent_path;
    }
  }

  public ParentPathNotFoundException deepCopy() {
    return new ParentPathNotFoundException(this);
  }

  @Override
  public void clear() {
    this.parent_path = null;
  }

  public String getParent_path() {
    return this.parent_path;
  }

  public ParentPathNotFoundException setParent_path(String parent_path) {
    this.parent_path = parent_path;
    return this;
  }

  public void unsetParent_path() {
    this.parent_path = null;
  }

  /** Returns true if field parent_path is set (has been assigned a value) and false otherwise */
  public boolean isSetParent_path() {
    return this.parent_path != null;
  }

  public void setParent_pathIsSet(boolean value) {
    if (!value) {
      this.parent_path = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PARENT_PATH:
      if (value == null) {
        unsetParent_path();
      } else {
        setParent_path((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PARENT_PATH:
      return getParent_path();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PARENT_PATH:
      return isSetParent_path();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ParentPathNotFoundException)
      return this.equals((ParentPathNotFoundException)that);
    return false;
  }

  public boolean equals(ParentPathNotFoundException that) {
    if (that == null)
      return false;

    boolean this_present_parent_path = true && this.isSetParent_path();
    boolean that_present_parent_path = true && that.isSetParent_path();
    if (this_present_parent_path || that_present_parent_path) {
      if (!(this_present_parent_path && that_present_parent_path))
        return false;
      if (!this.parent_path.equals(that.parent_path))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_parent_path = true && (isSetParent_path());
    list.add(present_parent_path);
    if (present_parent_path)
      list.add(parent_path);

    return list.hashCode();
  }

  @Override
  public int compareTo(ParentPathNotFoundException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetParent_path()).compareTo(other.isSetParent_path());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParent_path()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parent_path, other.parent_path);
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
    StringBuilder sb = new StringBuilder("ParentPathNotFoundException(");
    boolean first = true;

    sb.append("parent_path:");
    if (this.parent_path == null) {
      sb.append("null");
    } else {
      sb.append(this.parent_path);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (parent_path == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'parent_path' was not present! Struct: " + toString());
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ParentPathNotFoundExceptionStandardSchemeFactory implements SchemeFactory {
    public ParentPathNotFoundExceptionStandardScheme getScheme() {
      return new ParentPathNotFoundExceptionStandardScheme();
    }
  }

  private static class ParentPathNotFoundExceptionStandardScheme extends StandardScheme<ParentPathNotFoundException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ParentPathNotFoundException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PARENT_PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.parent_path = iprot.readString();
              struct.setParent_pathIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ParentPathNotFoundException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.parent_path != null) {
        oprot.writeFieldBegin(PARENT_PATH_FIELD_DESC);
        oprot.writeString(struct.parent_path);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ParentPathNotFoundExceptionTupleSchemeFactory implements SchemeFactory {
    public ParentPathNotFoundExceptionTupleScheme getScheme() {
      return new ParentPathNotFoundExceptionTupleScheme();
    }
  }

  private static class ParentPathNotFoundExceptionTupleScheme extends TupleScheme<ParentPathNotFoundException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ParentPathNotFoundException struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.parent_path);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ParentPathNotFoundException struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.parent_path = iprot.readString();
      struct.setParent_pathIsSet(true);
    }
  }

}

