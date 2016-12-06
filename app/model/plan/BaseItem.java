package model.plan;

import com.avaje.ebean.Model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Gukov on 06.12.2016.
 */

@DiscriminatorColumn(name = "TypeItem")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseItem extends Model {





}
