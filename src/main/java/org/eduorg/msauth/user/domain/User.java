package org.eduorg.msauth.user.domain;

import org.eduorg.msauth.common.domain.aggregate_root.AggregateRoot;
import org.eduorg.msauth.common.domain.domain_events.DomainEvents;
import org.eduorg.msauth.user.domain.events.UserBirthdateUpdatedEvent;
import org.eduorg.msauth.user.domain.events.UserCreatedEvent;
import org.eduorg.msauth.user.domain.events.UserNameUpdatedEvent;
import org.eduorg.msauth.user.domain.events.UserPhoneUpdatedEvent;
import org.eduorg.msauth.user.domain.exceptions.InvalidUserStateException;
import org.eduorg.msauth.user.domain.vo.*;

public class User extends AggregateRoot<UserId> {


    private final UserId id;
    private UserName name;
    private final UserEmail email;
    private UserPhone phone;
    private UserBirthdate birthdate;
    private UserGender gender;

    public User( UserId id, UserName name, UserEmail email, UserPhone phone, UserBirthdate birthdate, UserGender gender) {
        super( id, UserCreatedEvent.create( id.getId(), name.getFullName(), email.getEmail(), phone.getPhone(), birthdate.getBirthdate(), gender.toString() ));
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    @Override
    public void applyEvent(DomainEvents event) {

        switch (event.eventName())
        {
            case "UserNameUpdatedEvent":
                System.out.println("UserNameUpdatedEvent, old name: " + ((UserNameUpdatedEvent)event).getOldUserName().getFullName() + ", new name: " + ((UserNameUpdatedEvent)event).getNewUserName().getFullName());
                this.name = ((UserNameUpdatedEvent)event).getNewUserName();
                break;
            case "UserPhoneUpdatedEvent":
                System.out.println("UserPhoneUpdatedEvent, old phone: " + ((UserPhoneUpdatedEvent)event).getOldPhone().getPhone() + ", new phone: " + ((UserPhoneUpdatedEvent)event).getNewPhone().getPhone());
                this.phone = ((UserPhoneUpdatedEvent)event).getNewPhone();
                break;
            case "UserBirthdateUpdatedEvent":
                System.out.println("UserBirthdateUpdatedEvent, old birthdate: " + ((UserBirthdateUpdatedEvent)event).getOldBirthdate().getBirthdate() + ", new birthdate: " + ((UserBirthdateUpdatedEvent)event).getNewBirthdate().getBirthdate());
                this.birthdate = ((UserBirthdateUpdatedEvent)event).getNewBirthdate();
                break;
            default:
                System.out.println("Default Event?");
                break;
        }
    }

    @Override
    public void ensureValidState() throws InvalidUserStateException {
        if (
                this.id == null ||
                this.name == null ||
                this.email == null ||
                this.phone == null
        ) {
            throw new InvalidUserStateException();
        }
    }

    public UserName getName() {
        return name;
    }

    public UserEmail getEmail() {
        return email;
    }

    public UserPhone getPhone() {
        return phone;
    }

    public UserBirthdate getBirthdate(){
        return birthdate;
    }

    public UserGender getGender(){
        return gender;
    }


    public void updateName(UserName name) {
        this.applyEvent(
                UserNameUpdatedEvent.create(
                        this.id,
                        this.name,
                        name
                )
        );
    }

    public void updatePhone(UserPhone phone) {
        this.applyEvent(
                UserPhoneUpdatedEvent.create(
                        this.id,
                        this.phone,
                        phone
                )
        );
    }

    public void updateBirthdate(UserBirthdate birthdate) {
        this.applyEvent(
                UserBirthdateUpdatedEvent.create(
                        this.id,
                        this.birthdate,
                        birthdate
                )
        );
    }

    public static User create(
            UserId id, UserName name, UserEmail email, UserPhone phone, UserBirthdate birthdate, UserGender gender
    ) {
        return new User( id, name, email, phone, birthdate, gender );
    }
}
