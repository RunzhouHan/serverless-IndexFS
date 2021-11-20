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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2021-11-20")
public class ScanPlusResult implements org.apache.thrift.TBase<ScanPlusResult, ScanPlusResult._Fields>, java.io.Serializable, Cloneable, Comparable<ScanPlusResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ScanPlusResult");

  private static final org.apache.thrift.protocol.TField NAMES_FIELD_DESC = new org.apache.thrift.protocol.TField("names", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField ENTRIES_FIELD_DESC = new org.apache.thrift.protocol.TField("entries", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField END_KEY_FIELD_DESC = new org.apache.thrift.protocol.TField("end_key", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField END_PARTITION_FIELD_DESC = new org.apache.thrift.protocol.TField("end_partition", org.apache.thrift.protocol.TType.I16, (short)4);
  private static final org.apache.thrift.protocol.TField MORE_ENTRIES_FIELD_DESC = new org.apache.thrift.protocol.TField("more_entries", org.apache.thrift.protocol.TType.I16, (short)5);
  private static final org.apache.thrift.protocol.TField DMAP_DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("dmap_data", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ScanPlusResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ScanPlusResultTupleSchemeFactory());
  }

  public List<String> names; // required
  public List<StatInfo> entries; // required
  public String end_key; // required
  public short end_partition; // required
  public short more_entries; // required
  public String dmap_data; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAMES((short)1, "names"),
    ENTRIES((short)2, "entries"),
    END_KEY((short)3, "end_key"),
    END_PARTITION((short)4, "end_partition"),
    MORE_ENTRIES((short)5, "more_entries"),
    DMAP_DATA((short)6, "dmap_data");

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
        case 1: // NAMES
          return NAMES;
        case 2: // ENTRIES
          return ENTRIES;
        case 3: // END_KEY
          return END_KEY;
        case 4: // END_PARTITION
          return END_PARTITION;
        case 5: // MORE_ENTRIES
          return MORE_ENTRIES;
        case 6: // DMAP_DATA
          return DMAP_DATA;
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
  private static final int __END_PARTITION_ISSET_ID = 0;
  private static final int __MORE_ENTRIES_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAMES, new org.apache.thrift.meta_data.FieldMetaData("names", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.ENTRIES, new org.apache.thrift.meta_data.FieldMetaData("entries", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, StatInfo.class))));
    tmpMap.put(_Fields.END_KEY, new org.apache.thrift.meta_data.FieldMetaData("end_key", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.END_PARTITION, new org.apache.thrift.meta_data.FieldMetaData("end_partition", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.MORE_ENTRIES, new org.apache.thrift.meta_data.FieldMetaData("more_entries", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.DMAP_DATA, new org.apache.thrift.meta_data.FieldMetaData("dmap_data", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ScanPlusResult.class, metaDataMap);
  }

  public ScanPlusResult() {
  }

  public ScanPlusResult(
    List<String> names,
    List<StatInfo> entries,
    String end_key,
    short end_partition,
    short more_entries,
    String dmap_data)
  {
    this();
    this.names = names;
    this.entries = entries;
    this.end_key = end_key;
    this.end_partition = end_partition;
    setEnd_partitionIsSet(true);
    this.more_entries = more_entries;
    setMore_entriesIsSet(true);
    this.dmap_data = dmap_data;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ScanPlusResult(ScanPlusResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetNames()) {
      List<String> __this__names = new ArrayList<String>(other.names);
      this.names = __this__names;
    }
    if (other.isSetEntries()) {
      List<StatInfo> __this__entries = new ArrayList<StatInfo>(other.entries.size());
      for (StatInfo other_element : other.entries) {
        __this__entries.add(new StatInfo(other_element));
      }
      this.entries = __this__entries;
    }
    if (other.isSetEnd_key()) {
      this.end_key = other.end_key;
    }
    this.end_partition = other.end_partition;
    this.more_entries = other.more_entries;
    if (other.isSetDmap_data()) {
      this.dmap_data = other.dmap_data;
    }
  }

  public ScanPlusResult deepCopy() {
    return new ScanPlusResult(this);
  }

  @Override
  public void clear() {
    this.names = null;
    this.entries = null;
    this.end_key = null;
    setEnd_partitionIsSet(false);
    this.end_partition = 0;
    setMore_entriesIsSet(false);
    this.more_entries = 0;
    this.dmap_data = null;
  }

  public int getNamesSize() {
    return (this.names == null) ? 0 : this.names.size();
  }

  public java.util.Iterator<String> getNamesIterator() {
    return (this.names == null) ? null : this.names.iterator();
  }

  public void addToNames(String elem) {
    if (this.names == null) {
      this.names = new ArrayList<String>();
    }
    this.names.add(elem);
  }

  public List<String> getNames() {
    return this.names;
  }

  public ScanPlusResult setNames(List<String> names) {
    this.names = names;
    return this;
  }

  public void unsetNames() {
    this.names = null;
  }

  /** Returns true if field names is set (has been assigned a value) and false otherwise */
  public boolean isSetNames() {
    return this.names != null;
  }

  public void setNamesIsSet(boolean value) {
    if (!value) {
      this.names = null;
    }
  }

  public int getEntriesSize() {
    return (this.entries == null) ? 0 : this.entries.size();
  }

  public java.util.Iterator<StatInfo> getEntriesIterator() {
    return (this.entries == null) ? null : this.entries.iterator();
  }

  public void addToEntries(StatInfo elem) {
    if (this.entries == null) {
      this.entries = new ArrayList<StatInfo>();
    }
    this.entries.add(elem);
  }

  public List<StatInfo> getEntries() {
    return this.entries;
  }

  public ScanPlusResult setEntries(List<StatInfo> entries) {
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

  public String getEnd_key() {
    return this.end_key;
  }

  public ScanPlusResult setEnd_key(String end_key) {
    this.end_key = end_key;
    return this;
  }

  public void unsetEnd_key() {
    this.end_key = null;
  }

  /** Returns true if field end_key is set (has been assigned a value) and false otherwise */
  public boolean isSetEnd_key() {
    return this.end_key != null;
  }

  public void setEnd_keyIsSet(boolean value) {
    if (!value) {
      this.end_key = null;
    }
  }

  public short getEnd_partition() {
    return this.end_partition;
  }

  public ScanPlusResult setEnd_partition(short end_partition) {
    this.end_partition = end_partition;
    setEnd_partitionIsSet(true);
    return this;
  }

  public void unsetEnd_partition() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __END_PARTITION_ISSET_ID);
  }

  /** Returns true if field end_partition is set (has been assigned a value) and false otherwise */
  public boolean isSetEnd_partition() {
    return EncodingUtils.testBit(__isset_bitfield, __END_PARTITION_ISSET_ID);
  }

  public void setEnd_partitionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __END_PARTITION_ISSET_ID, value);
  }

  public short getMore_entries() {
    return this.more_entries;
  }

  public ScanPlusResult setMore_entries(short more_entries) {
    this.more_entries = more_entries;
    setMore_entriesIsSet(true);
    return this;
  }

  public void unsetMore_entries() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MORE_ENTRIES_ISSET_ID);
  }

  /** Returns true if field more_entries is set (has been assigned a value) and false otherwise */
  public boolean isSetMore_entries() {
    return EncodingUtils.testBit(__isset_bitfield, __MORE_ENTRIES_ISSET_ID);
  }

  public void setMore_entriesIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MORE_ENTRIES_ISSET_ID, value);
  }

  public String getDmap_data() {
    return this.dmap_data;
  }

  public ScanPlusResult setDmap_data(String dmap_data) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAMES:
      if (value == null) {
        unsetNames();
      } else {
        setNames((List<String>)value);
      }
      break;

    case ENTRIES:
      if (value == null) {
        unsetEntries();
      } else {
        setEntries((List<StatInfo>)value);
      }
      break;

    case END_KEY:
      if (value == null) {
        unsetEnd_key();
      } else {
        setEnd_key((String)value);
      }
      break;

    case END_PARTITION:
      if (value == null) {
        unsetEnd_partition();
      } else {
        setEnd_partition((Short)value);
      }
      break;

    case MORE_ENTRIES:
      if (value == null) {
        unsetMore_entries();
      } else {
        setMore_entries((Short)value);
      }
      break;

    case DMAP_DATA:
      if (value == null) {
        unsetDmap_data();
      } else {
        setDmap_data((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAMES:
      return getNames();

    case ENTRIES:
      return getEntries();

    case END_KEY:
      return getEnd_key();

    case END_PARTITION:
      return getEnd_partition();

    case MORE_ENTRIES:
      return getMore_entries();

    case DMAP_DATA:
      return getDmap_data();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAMES:
      return isSetNames();
    case ENTRIES:
      return isSetEntries();
    case END_KEY:
      return isSetEnd_key();
    case END_PARTITION:
      return isSetEnd_partition();
    case MORE_ENTRIES:
      return isSetMore_entries();
    case DMAP_DATA:
      return isSetDmap_data();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ScanPlusResult)
      return this.equals((ScanPlusResult)that);
    return false;
  }

  public boolean equals(ScanPlusResult that) {
    if (that == null)
      return false;

    boolean this_present_names = true && this.isSetNames();
    boolean that_present_names = true && that.isSetNames();
    if (this_present_names || that_present_names) {
      if (!(this_present_names && that_present_names))
        return false;
      if (!this.names.equals(that.names))
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

    boolean this_present_end_key = true && this.isSetEnd_key();
    boolean that_present_end_key = true && that.isSetEnd_key();
    if (this_present_end_key || that_present_end_key) {
      if (!(this_present_end_key && that_present_end_key))
        return false;
      if (!this.end_key.equals(that.end_key))
        return false;
    }

    boolean this_present_end_partition = true;
    boolean that_present_end_partition = true;
    if (this_present_end_partition || that_present_end_partition) {
      if (!(this_present_end_partition && that_present_end_partition))
        return false;
      if (this.end_partition != that.end_partition)
        return false;
    }

    boolean this_present_more_entries = true;
    boolean that_present_more_entries = true;
    if (this_present_more_entries || that_present_more_entries) {
      if (!(this_present_more_entries && that_present_more_entries))
        return false;
      if (this.more_entries != that.more_entries)
        return false;
    }

    boolean this_present_dmap_data = true && this.isSetDmap_data();
    boolean that_present_dmap_data = true && that.isSetDmap_data();
    if (this_present_dmap_data || that_present_dmap_data) {
      if (!(this_present_dmap_data && that_present_dmap_data))
        return false;
      if (!this.dmap_data.equals(that.dmap_data))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_names = true && (isSetNames());
    list.add(present_names);
    if (present_names)
      list.add(names);

    boolean present_entries = true && (isSetEntries());
    list.add(present_entries);
    if (present_entries)
      list.add(entries);

    boolean present_end_key = true && (isSetEnd_key());
    list.add(present_end_key);
    if (present_end_key)
      list.add(end_key);

    boolean present_end_partition = true;
    list.add(present_end_partition);
    if (present_end_partition)
      list.add(end_partition);

    boolean present_more_entries = true;
    list.add(present_more_entries);
    if (present_more_entries)
      list.add(more_entries);

    boolean present_dmap_data = true && (isSetDmap_data());
    list.add(present_dmap_data);
    if (present_dmap_data)
      list.add(dmap_data);

    return list.hashCode();
  }

  @Override
  public int compareTo(ScanPlusResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetNames()).compareTo(other.isSetNames());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNames()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.names, other.names);
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
    lastComparison = Boolean.valueOf(isSetEnd_key()).compareTo(other.isSetEnd_key());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnd_key()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.end_key, other.end_key);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEnd_partition()).compareTo(other.isSetEnd_partition());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnd_partition()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.end_partition, other.end_partition);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMore_entries()).compareTo(other.isSetMore_entries());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMore_entries()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.more_entries, other.more_entries);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
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
    StringBuilder sb = new StringBuilder("ScanPlusResult(");
    boolean first = true;

    sb.append("names:");
    if (this.names == null) {
      sb.append("null");
    } else {
      sb.append(this.names);
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
    if (!first) sb.append(", ");
    sb.append("end_key:");
    if (this.end_key == null) {
      sb.append("null");
    } else {
      sb.append(this.end_key);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("end_partition:");
    sb.append(this.end_partition);
    first = false;
    if (!first) sb.append(", ");
    sb.append("more_entries:");
    sb.append(this.more_entries);
    first = false;
    if (!first) sb.append(", ");
    sb.append("dmap_data:");
    if (this.dmap_data == null) {
      sb.append("null");
    } else {
      sb.append(this.dmap_data);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (names == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'names' was not present! Struct: " + toString());
    }
    if (entries == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'entries' was not present! Struct: " + toString());
    }
    if (end_key == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'end_key' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'end_partition' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'more_entries' because it's a primitive and you chose the non-beans generator.
    if (dmap_data == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'dmap_data' was not present! Struct: " + toString());
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

  private static class ScanPlusResultStandardSchemeFactory implements SchemeFactory {
    public ScanPlusResultStandardScheme getScheme() {
      return new ScanPlusResultStandardScheme();
    }
  }

  private static class ScanPlusResultStandardScheme extends StandardScheme<ScanPlusResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ScanPlusResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAMES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list24 = iprot.readListBegin();
                struct.names = new ArrayList<String>(_list24.size);
                String _elem25;
                for (int _i26 = 0; _i26 < _list24.size; ++_i26)
                {
                  _elem25 = iprot.readString();
                  struct.names.add(_elem25);
                }
                iprot.readListEnd();
              }
              struct.setNamesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ENTRIES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list27 = iprot.readListBegin();
                struct.entries = new ArrayList<StatInfo>(_list27.size);
                StatInfo _elem28;
                for (int _i29 = 0; _i29 < _list27.size; ++_i29)
                {
                  _elem28 = new StatInfo();
                  _elem28.read(iprot);
                  struct.entries.add(_elem28);
                }
                iprot.readListEnd();
              }
              struct.setEntriesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // END_KEY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.end_key = iprot.readString();
              struct.setEnd_keyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // END_PARTITION
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.end_partition = iprot.readI16();
              struct.setEnd_partitionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // MORE_ENTRIES
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.more_entries = iprot.readI16();
              struct.setMore_entriesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // DMAP_DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dmap_data = iprot.readString();
              struct.setDmap_dataIsSet(true);
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
      if (!struct.isSetEnd_partition()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'end_partition' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetMore_entries()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'more_entries' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ScanPlusResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.names != null) {
        oprot.writeFieldBegin(NAMES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.names.size()));
          for (String _iter30 : struct.names)
          {
            oprot.writeString(_iter30);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.entries != null) {
        oprot.writeFieldBegin(ENTRIES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.entries.size()));
          for (StatInfo _iter31 : struct.entries)
          {
            _iter31.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.end_key != null) {
        oprot.writeFieldBegin(END_KEY_FIELD_DESC);
        oprot.writeString(struct.end_key);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(END_PARTITION_FIELD_DESC);
      oprot.writeI16(struct.end_partition);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MORE_ENTRIES_FIELD_DESC);
      oprot.writeI16(struct.more_entries);
      oprot.writeFieldEnd();
      if (struct.dmap_data != null) {
        oprot.writeFieldBegin(DMAP_DATA_FIELD_DESC);
        oprot.writeString(struct.dmap_data);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ScanPlusResultTupleSchemeFactory implements SchemeFactory {
    public ScanPlusResultTupleScheme getScheme() {
      return new ScanPlusResultTupleScheme();
    }
  }

  private static class ScanPlusResultTupleScheme extends TupleScheme<ScanPlusResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ScanPlusResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.names.size());
        for (String _iter32 : struct.names)
        {
          oprot.writeString(_iter32);
        }
      }
      {
        oprot.writeI32(struct.entries.size());
        for (StatInfo _iter33 : struct.entries)
        {
          _iter33.write(oprot);
        }
      }
      oprot.writeString(struct.end_key);
      oprot.writeI16(struct.end_partition);
      oprot.writeI16(struct.more_entries);
      oprot.writeString(struct.dmap_data);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ScanPlusResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list34 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.names = new ArrayList<String>(_list34.size);
        String _elem35;
        for (int _i36 = 0; _i36 < _list34.size; ++_i36)
        {
          _elem35 = iprot.readString();
          struct.names.add(_elem35);
        }
      }
      struct.setNamesIsSet(true);
      {
        org.apache.thrift.protocol.TList _list37 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.entries = new ArrayList<StatInfo>(_list37.size);
        StatInfo _elem38;
        for (int _i39 = 0; _i39 < _list37.size; ++_i39)
        {
          _elem38 = new StatInfo();
          _elem38.read(iprot);
          struct.entries.add(_elem38);
        }
      }
      struct.setEntriesIsSet(true);
      struct.end_key = iprot.readString();
      struct.setEnd_keyIsSet(true);
      struct.end_partition = iprot.readI16();
      struct.setEnd_partitionIsSet(true);
      struct.more_entries = iprot.readI16();
      struct.setMore_entriesIsSet(true);
      struct.dmap_data = iprot.readString();
      struct.setDmap_dataIsSet(true);
    }
  }

}

