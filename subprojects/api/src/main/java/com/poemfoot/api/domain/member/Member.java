package com.poemfoot.api.domain.member;

import com.poemfoot.api.domain.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"deviceId"})
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_os")
    private DeviceOsType deviceOs;

    @Column(name = "nickname")
    private String nickname;

    private Long maxNumber;

    public Member(String deviceId, DeviceOsType deviceOs, String nickname) {
        this.deviceId = deviceId;
        this.deviceOs = deviceOs;
        this.nickname = nickname;
        this.maxNumber = 0L;
    }

    public Long plusMaxNumber(){
        return ++maxNumber;
    }
}
