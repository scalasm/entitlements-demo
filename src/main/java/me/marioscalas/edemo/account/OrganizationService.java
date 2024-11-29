package me.marioscalas.edemo.account;

public interface OrganizationService {
    Account createDistrict(String name);
    Account createSchool(String districtId, String name);
}
