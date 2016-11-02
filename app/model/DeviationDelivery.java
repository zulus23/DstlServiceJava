package model;

import javax.persistence.*;

/**
 * Created by Zhukov on 22.10.2016.
 */
@Entity
@DiscriminatorValue(value = "Доставка")
public class DeviationDelivery extends BaseDeviation {



}
