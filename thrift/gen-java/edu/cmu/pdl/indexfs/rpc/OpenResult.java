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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2021-06-10")
public class OpenResult implements org.apache.thrift.TBase<OpenResult, OpenResult._Fields>, java.io.Serializable, Cloneable, Comparable<OpenResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("OpenResult");

  private static final org.apache.thrift.protocol.TField IS_EMBEDDED_FIELD_DESC = new org.apache.thrift.protocol.TField("is_embedded", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("data", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new OpenResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new OpenResultTupleSchemeFactory());
  }

  public boolean is_embedded; // required
  public String data; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    IS_EMBEDDED((short)1, "is_embedded"),
    DATA((short)2, "data");

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
        case 1: // IS_EMBEDDED
          return IS_EMBEDDED;
        case 2: // DATA
          return DATA;
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
  private static final int __IS_EMBEDDED_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.IS_EMBEDDED, new org.apache.thrift.meta_data.FieldMetaData("is_embedded", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.DATA, new org.apache.thrift.meta_data.FieldMetaData("data", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(OpenResult.class, metaDataMap);
  }

  public OpenResult() {
  }

  public OpenResult(
    boolean is_embedded,
    String data)
  {
    this();
    this.is_embedded = is_embedded;
    setIs_embeddedIsSet(true);
    this.data = data;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public OpenResult(OpenResult other) {
    __isset_bitfield = other.__isset_bitfield;
    this.is_embedded = other.is_embedded;
    if (other.isSetData()) {
      this.data = other.data;
    }
  }

  public OpenResult deepCopy() {
    return new OpenResult(this);
  }

  @Override
  public void clear() {
    setIs_embeddedIsSet(false);
    this.is_embedded = false;
    this.data = null;
  }

  public boolean isIs_embedded() {
    return this.is_embedded;
  }

  public OpenResult setIs_embedded(boolean is_embedded) {
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

  public String getData() {
    return this.data;
  }

  public OpenResult setData(String data) {
    this.data = data;
    return this;
  }

  public void unsetData() {
    this.data = null;
  }

  /** Returns true if field data is set (has been assigned a value) and false otherwise */
  public boolean isSetData() {
    return this.data != null;
  }

  public void setDataIsSet(boolean value) {
    if (!value) {
      this.data = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case IS_EMBEDDED:
      if (value == null) {
        unsetIs_embedded();
      } else {
        setIs_embedded((Boolean)value);
      }
      break;

    case DATA:
      if (value == null) {
        unsetData();
      } else {
        setData((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case IS_EMBEDDED:
      return isIs_embedded();

    case DATA:
      return getData();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case IS_EMBEDDED:
      return isSetIs_embedded();
    case DATA:
      return isSetData();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof OpenResult)
      return this.equals((OpenResult)that);
    return false;
  }

  public boolean equals(OpenResult that) {
    if (that == null)
      return false;

    boolean this_present_is_embedded = true;
    boolean that_present_is_embedded = true;
    if (this_present_is_embedded || that_present_is_embedded) {
      if (!(this_present_is_embedded && that_present_is_embedded))
        return false;
      if (this.is_embedded != that.is_embedded)
        return false;
    }

    boolean this_present_data = true && this.isSetData();
    boolean that_present_data = true && that.isSetData();
    if (this_present_data || that_present_data) {
      if (!(this_present_data && that_present_data))
        return false;
      if (!this.data.equals(that.data))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_is_embedded = true;
    list.add(present_is_embedded);
    if (present_is_embedded)
      list.add(is_embedded);

    boolean present_data = true && (isSetData());
    list.add(present_data);
    if (present_data)
      list.add(data);

    return list.hashCode();
  }

  @Override
  public int compareTo(OpenResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetData()).compareTo(other.isSetData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.data, other.data);
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
    StringBuilder sb = new StringBuilder("OpenResult(");
    boolean first = true;

    sb.append("is_embedded:");
    sb.append(this.is_embedded);
    first = false;
    if (!first) sb.append(", ");
    sb.append("data:");
    if (this.data == null) {
      sb.append("null");
    } else {
      sb.append(this.data);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'is_embedded' because it's a primitive and you chose the non-beans generator.
    if (data == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'data' was not present! Struct: " + toString());
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

  private static class OpenResultStandardSchemeFactory implements SchemeFactory {
    public OpenResultStandardScheme getScheme() {
      return new OpenResultStandardScheme();
    }
  }

  private static class OpenResultStandardScheme extends StandardScheme<OpenResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, OpenResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // IS_EMBEDDED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.is_embedded = iprot.readBool();
              struct.setIs_embeddedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.data = iprot.readString();
              struct.setDataIsSet(true);
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
      if (!struct.isSetIs_embedded()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'is_embedded' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, OpenResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(IS_EMBEDDED_FIELD_DESC);
      oprot.writeBool(struct.is_embedded);
      oprot.writeFieldEnd();
      if (struct.data != null) {
        oprot.writeFieldBegin(DATA_FIELD_DESC);
        oprot.writeString(struct.data);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class OpenResultTupleSchemeFactory implements SchemeFactory {
    public OpenResultTupleScheme getScheme() {
      return new OpenResultTupleScheme();
    }
  }

  private static class OpenResultTupleScheme extends TupleScheme<OpenResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, OpenResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeBool(struct.is_embedded);
      oprot.writeString(struct.data);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, OpenResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.is_embedded = iprot.readBool();
      struct.setIs_embeddedIsSet(true);
      struct.data = iprot.readString();
      struct.setDataIsSet(true);
    }
  }

}

