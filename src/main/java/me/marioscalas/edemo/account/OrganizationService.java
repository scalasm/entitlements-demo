package me.marioscalas.edemo.account;

/**
 * Service to manage the organization structure.
 */
public interface OrganizationService {
    Account createDistrict(String districtName);
    Account createSchool(String districtId, String schoolName);
    Account createClass(String schoolId, String className);
    Account createStudent(String studentName);

    void addStudentToClass(String studentId, String classId);

    void relate(String sourceAccountId, String targetAccountId, AccountRelationshipType type);
    void relate(Account sourceAccount, Account targetAccount, AccountRelationshipType type);
}
