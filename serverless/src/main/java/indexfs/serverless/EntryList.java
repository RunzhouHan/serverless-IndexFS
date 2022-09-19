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
public class EntryList implements org.apache.thrift.TBase<EntryList, EntryList._Fields>, java.io.Serializable, Cloneable, Comparable<EntryList> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("EntryList");

  private static final org.apache.thrift.protocol.TField DMAP_DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("dmap_data", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ENTRIES_FIELD_DESC = new org.apache.thrift.protocol.TField("entries", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new EntryListStandardSchemeFactory());
    schemes.put(TupleScheme.class, new EntryListTupleSchemeFactory());
  }

  public String dmap_data; // required
  public List<String> entries; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DMAP_DATA((short)1, "dmap_data"),
    ENTRIES((short)2, "entries");

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
        case 1: // DMAP_DATA
          return DMAP_DATA;
        case 2: // ENTRIES
          return ENTRIES;
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
    tmpMap.put(_Fields.DMAP_DATA, new org.apache.thrift.meta_data.FieldMetaData("dmap_data", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENTRIES, new org.apache.thrift.meta_data.FieldMetaData("entries", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(EntryList.class, metaDataMap);
  }

  public EntryList() {
  }

  public EntryList(
    String dmap_data,
    List<String> entries)
  {
    this();
    this.dmap_data = dmap_data;
    this.entries = entries;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public EntryList(EntryList other) {
    if (other.isSetDmap_data()) {
      this.dmap_data = other.dmap_data;
    }
    if (other.isSetEntries()) {
      List<String> __this__entries = new ArrayList<String>(other.entries);
      this.entries = __this__entries;
    }
  }

  public EntryList deepCopy() {
    return new EntryList(this);
  }

  @Override
  public void clear() {
    this.dmap_data = null;
    this.entries = null;
  }

  public String getDmap_data() {
    return this.dmap_data;
  }

  public EntryList setDmap_data(String dmap_data) {
    this.dmap_data = dmap_data;
    return this;
  }

  public void unsetDmap_data() {
    this.dmap_data = null;
  }

  /** Returns true if field dmap_data is set (has been assigned a value) and false otherwise */
  public boolean isSetDmap_data() {
    return this.dmap_data != null;
  }

  public void setDmap_dataIsSet(boolean value) {
    if (!value) {
      this.dmap_data = null;
    }
  }

  public int getEntriesSize() {
    return (this.entries == null) ? 0 : this.entries.size();
  }

  public java.util.Iterator<String> getEntriesIterator() {
    return (this.entries == null) ? null : this.entries.iterator();
  }

  public void addToEntries(String elem) {
    if (this.entries == null) {
      this.entries = new ArrayList<String>();
    }
    this.entries.add(elem);
  }

  public List<String> getEntries() {
    return this.entries;
  }

  public EntryList setEntries(List<String> entries) {
    this.entries = entries;
    return this;
  }

  public void unsetEntries() {
    this.entries = null;
  }

  /** Returns true if field entries is set (has been assigned a value) and false otherwise */
  public boolean isSetEntries() {
    return this.entries != null;
  }

  public void setEntriesIsSet(boolean value) {
    if (!value) {
      this.entries = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DMAP_DATA:
      if (value == null) {
        unsetDmap_data();
      } else {
        setDmap_data((String)value);
      }
      break;

    case ENTRIES:
      if (value == null) {
        unsetEntries();
      } else {
        setEntries((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DMAP_DATA:
      return getDmap_data();

    case ENTRIES:
      return getEntries();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DMAP_DATA:
      return isSetDmap_data();
    case ENTRIES:
      return isSetEntries();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof EntryList)
      return this.equals((EntryList)that);
    return false;
  }

  public boolean equals(EntryList that) {
    if (that == null)
      return false;

    boolean this_present_dmap_data = true && this.isSetDmap_data();
    boolean that_present_dmap_data = true && that.isSetDmap_data();
    if (this_present_dmap_data || that_present_dmap_data) {
      if (!(this_present_dmap_data && that_present_dmap_data))
        return false;
      if (!this.dmap_data.equals(that.dmap_data))
        return false;
    }

    boolean this_present_entries = true && this.isSetEntries();
    boolean that_present_entries = true && that.isSetEntries();
    if (this_present_entries || that_present_entries) {
      if (!(this_present_entries && that_present_entries))
        return false;
      if (!this.entries.equals(that.entries))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_dmap_data = true && (isSetDmap_data());
    list.add(present_dmap_data);
    if (present_dmap_data)
      list.add(dmap_data);

    boolean present_entries = true && (isSetEntries());
    list.add(present_entries);
    if (present_entries)
      list.add(entries);

    return list.hashCode();
  }

  @Override
  public int compareTo(EntryList other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDmap_data()).compareTo(other.isSetDmap_data());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDmap_data()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dmap_data, other.dmap_data);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEntries()).compareTo(other.isSetEntries());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEntries()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.entries, other.entries);
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
    StringBuilder sb = new StringBuilder("EntryList(");
    boolean first = true;

    sb.append("dmap_data:");
    if (this.dmap_data == null) {
      sb.append("null");
    } else {
      sb.append(this.dmap_data);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("entries:");
    if (this.entries == null) {
      sb.append("null");
    } else {
      sb.append(this.entries);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (dmap_data == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'dmap_data' was not present! Struct: " + toString());
    }
    if (entries == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'entries' was not present! Struct: " + toString());
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

  private static class EntryListStandardSchemeFactory implements SchemeFactory {
    public EntryListStandardScheme getScheme() {
      return new EntryListStandardScheme();
    }
  }

  private static class EntryListStandardScheme extends StandardScheme<EntryList> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, EntryList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DMAP_DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dmap_data = iprot.readString();
              struct.setDmap_dataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ENTRIES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.entries = new ArrayList<String>(_list8.size);
                String _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = iprot.readString();
                  struct.entries.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setEntriesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, EntryList struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dmap_data != null) {
        oprot.writeFieldBegin(DMAP_DATA_FIELD_DESC);
        oprot.writeString(struct.dmap_data);
        oprot.writeFieldEnd();
      }
      if (struct.entries != null) {
        oprot.writeFieldBegin(ENTRIES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.entries.size()));
          for (String _iter11 : struct.entries)
          {
            oprot.writeString(_iter11);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class EntryListTupleSchemeFactory implements SchemeFactory {
    public EntryListTupleScheme getScheme() {
      return new EntryListTupleScheme();
    }
  }

  private static class EntryListTupleScheme extends TupleScheme<EntryList> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, EntryList struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.dmap_data);
      {
        oprot.writeI32(struct.entries.size());
        for (String _iter12 : struct.entries)
        {
          oprot.writeString(_iter12);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, EntryList struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.dmap_data = iprot.readString();
      struct.setDmap_dataIsSet(true);
      {
        org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.entries = new ArrayList<String>(_list13.size);
        String _elem14;
        for (int _i15 = 0; _i15 < _list13.size; ++_i15)
        {
          _elem14 = iprot.readString();
          struct.entries.add(_elem14);
        }
      }
      struct.setEntriesIsSet(true);
    }
  }

}

