//package bg.bilet4e.prototype.shop;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//
//import bg.bilet4e.prototype.shop.work.time.WeeklyWorkingTime;
//import bg.bilet4e.prototype.user.owner.Owner;
//
//@Entity
//public class Shop {
//
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//
//    @NotBlank
//    @Column(name = "NAME")
//    private String name;
//
////    @Valid
////    @Column(name = "ADDRESS")
////    private Address address;
////
////    @Valid
////    @Column(name = "COORDINATES")
////    private Coordinates coordinates;
//
//    @Valid
//    @Column(name = "WORK_TIME")
//    private WeeklyWorkingTime workTime;
//
//    @ManyToOne
//    @JoinColumn(name = "OWNER_ID")
//    private Owner ownerId;
//
////    @OneToMany(targetEntity = Image.class)
////    @Column(name = "IMAGES")
////    private List<Image> images;
//}