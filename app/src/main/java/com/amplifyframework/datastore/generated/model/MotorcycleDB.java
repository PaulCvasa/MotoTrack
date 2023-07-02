package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the MotorcycleDB type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "MotorcycleDBS", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class MotorcycleDB implements Model {
  public static final QueryField ID = field("MotorcycleDB", "id");
  public static final QueryField VIN = field("MotorcycleDB", "vin");
  public static final QueryField MILEAGE = field("MotorcycleDB", "mileage");
  public static final QueryField MOTO_BRAND = field("MotorcycleDB", "motoBrand");
  public static final QueryField MOTO_MODEL = field("MotorcycleDB", "motoModel");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String vin;
  private final @ModelField(targetType="Int") Integer mileage;
  private final @ModelField(targetType="String") String motoBrand;
  private final @ModelField(targetType="String") String motoModel;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getVin() {
      return vin;
  }
  
  public Integer getMileage() {
      return mileage;
  }
  
  public String getMotoBrand() {
      return motoBrand;
  }
  
  public String getMotoModel() {
      return motoModel;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private MotorcycleDB(String id, String vin, Integer mileage, String motoBrand, String motoModel) {
    this.id = id;
    this.vin = vin;
    this.mileage = mileage;
    this.motoBrand = motoBrand;
    this.motoModel = motoModel;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      MotorcycleDB motorcycleDb = (MotorcycleDB) obj;
      return ObjectsCompat.equals(getId(), motorcycleDb.getId()) &&
              ObjectsCompat.equals(getVin(), motorcycleDb.getVin()) &&
              ObjectsCompat.equals(getMileage(), motorcycleDb.getMileage()) &&
              ObjectsCompat.equals(getMotoBrand(), motorcycleDb.getMotoBrand()) &&
              ObjectsCompat.equals(getMotoModel(), motorcycleDb.getMotoModel()) &&
              ObjectsCompat.equals(getCreatedAt(), motorcycleDb.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), motorcycleDb.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getVin())
      .append(getMileage())
      .append(getMotoBrand())
      .append(getMotoModel())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("MotorcycleDB {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("vin=" + String.valueOf(getVin()) + ", ")
      .append("mileage=" + String.valueOf(getMileage()) + ", ")
      .append("motoBrand=" + String.valueOf(getMotoBrand()) + ", ")
      .append("motoModel=" + String.valueOf(getMotoModel()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static MotorcycleDB justId(String id) {
    return new MotorcycleDB(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      vin,
      mileage,
      motoBrand,
      motoModel);
  }
  public interface BuildStep {
    MotorcycleDB build();
    BuildStep id(String id);
    BuildStep vin(String vin);
    BuildStep mileage(Integer mileage);
    BuildStep motoBrand(String motoBrand);
    BuildStep motoModel(String motoModel);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String vin;
    private Integer mileage;
    private String motoBrand;
    private String motoModel;
    @Override
     public MotorcycleDB build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new MotorcycleDB(
          id,
          vin,
          mileage,
          motoBrand,
          motoModel);
    }
    
    @Override
     public BuildStep vin(String vin) {
        this.vin = vin;
        return this;
    }
    
    @Override
     public BuildStep mileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }
    
    @Override
     public BuildStep motoBrand(String motoBrand) {
        this.motoBrand = motoBrand;
        return this;
    }
    
    @Override
     public BuildStep motoModel(String motoModel) {
        this.motoModel = motoModel;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String vin, Integer mileage, String motoBrand, String motoModel) {
      super.id(id);
      super.vin(vin)
        .mileage(mileage)
        .motoBrand(motoBrand)
        .motoModel(motoModel);
    }
    
    @Override
     public CopyOfBuilder vin(String vin) {
      return (CopyOfBuilder) super.vin(vin);
    }
    
    @Override
     public CopyOfBuilder mileage(Integer mileage) {
      return (CopyOfBuilder) super.mileage(mileage);
    }
    
    @Override
     public CopyOfBuilder motoBrand(String motoBrand) {
      return (CopyOfBuilder) super.motoBrand(motoBrand);
    }
    
    @Override
     public CopyOfBuilder motoModel(String motoModel) {
      return (CopyOfBuilder) super.motoModel(motoModel);
    }
  }
  
}
