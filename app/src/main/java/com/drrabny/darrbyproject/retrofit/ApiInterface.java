package com.drrabny.darrbyproject.retrofit;

import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.AddExperience;
import com.drrabny.darrbyproject.pojoClasses.AddLanguagetoStudent;
import com.drrabny.darrbyproject.pojoClasses.AddSkill;
import com.drrabny.darrbyproject.pojoClasses.Addlanguage;
import com.drrabny.darrbyproject.pojoClasses.ChangeStudentPassword;
import com.drrabny.darrbyproject.pojoClasses.Company;
import com.drrabny.darrbyproject.pojoClasses.DeleteLanguage;
import com.drrabny.darrbyproject.pojoClasses.DeleteSkill;
import com.drrabny.darrbyproject.pojoClasses.Exam;
import com.drrabny.darrbyproject.pojoClasses.Feed;
import com.drrabny.darrbyproject.pojoClasses.ResponseFollow;
import com.drrabny.darrbyproject.pojoClasses.ResponseRegister.ResponseRegister;
import com.drrabny.darrbyproject.pojoClasses.ResponseSkills.ResponseSkills;
import com.drrabny.darrbyproject.pojoClasses.ResponserSearch.ResponseSearch;
import com.drrabny.darrbyproject.pojoClasses.StudentRegister;
import com.drrabny.darrbyproject.pojoClasses.UpdateStudentAcountInformation;
import com.drrabny.darrbyproject.pojoClasses.UpdateStudentpersonalInformation;
import com.drrabny.darrbyproject.pojoClasses.UserLogin;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ahmad on 10-Apr-18.
 */

public interface ApiInterface {

    //login method
    @POST("userLogin")
    Call<UserLogin> LOGIN_RESULT_CALL(@Query("email") String email, @Query("password") String password);

    //register
    @FormUrlEncoded
    @POST("userRegister")
    Call<ResponseRegister> STUDENT_REGISTER_CALL(
            @Query("name") String name,
            @Field("lname") String lname,
            @Field("gender") String gender,
            @Field("email") String email,
            @Field("date_of_birth") String birth,
            @Field("username") String username,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("type") String type,
            @Field("profile_photo") String pic);


    @PUT("Student/Details")
    Call<AccountDetail> ACCOUNT_DETAIL_CALL(@Header("Authorization") String Token);

    @PUT("Student/feeds")
    Call<Feed> FEED_CALL(@Header("Authorization") String Token);

    @FormUrlEncoded
    @POST("Student/Personal")
    Call<UpdateStudentpersonalInformation> UPDATE_STUDENTPERSONAL_INFORMATION_CALL(
            @Header("Authorization") String Token,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("job_title") String job_title,
            @Field("country") String country,
            @Field("city") String city,
            @Field("governorate") String governorate,
            @Field("university") String university,
            @Field("faculty") String faculty,
            @Field("gender") String gender,
            @Field("marital_status") String marital_status,
            @Field("military_status") String military_status,
            @Field("phone") String phone,
            @Field("date_of_birth") String date_of_birth,
            @Field("cover_letter") String cover_letter,
            @Field("Linkedin") String Linkedin,
            @Field("skype") String skype,
            @Field("profile_photo") String profile_photo);

    //Company profile
    @PUT("Student/Company/{id}")
    Call<Company> COMPANY_CALL(@Header("Authorization") String Authorization,
                               @Path("id") int id);

    //Expeience , Skills , Projects
    @PUT("Student/Details")
    Call<AccountDetail> EXPERIENCE_CALL(@Header("Authorization") String Authorization);

    //Update Student Acount Information
    @POST("api/Student/Account")
    Call<UpdateStudentAcountInformation> UPDATE_STUDENT_ACOUNT_INFORMATION_CALL(@Header("Authorization") String tokin,
                                                                                @Query("email") String email,
                                                                                @Query("username") String username,
                                                                                @Query("password") String password);

    //update password
    @POST("Student/ChangePassword")
    Call<ChangeStudentPassword> CHANGE_STUDENT_PASSWORD_CALL(@Header("Authorization") String token,
                                                             @Query("current-password") String current_pass,
                                                             @Query("new-password") String newPass,
                                                             @Query("new-password_confirmation") String rePass);


    //get  exam
    @PUT("Student/exam/1")
    Call<Exam> EXAM_CALL(@Header("Authorization") String token);

    @POST("Student/Language")
    Call<Addlanguage> ADDLANGUAGE_CALL(@Header("Authorization") String Authorization,
                                       @Query("language") String language, @Query("level") int level);

    @POST("Student/Experience")
    Call<AddExperience> ADD_EXPERIENCE_CALL(@Header("Authorization") String Authorization, @Query("title") String title
            , @Query("start") String start, @Query("end") String end, @Query("description") String description);

    @PUT("Student/attachSkill/{id}")
    Call<ResponseFollow> ADD_SKILL_CALL(@Header("Authorization") String Authorization,@Path("id")int id);

    @DELETE("Student/DetachSkill/{id}")
    Call<DeleteSkill> DELETE_SKILL_CALL(@Header("Authorization") String Authorization, @Path("id") int id);

    @DELETE("Student/Language/{id}")
    Call<DeleteLanguage> DELETE_LANGUAGE_CALL(@Header("Authorization") String Authorization, @Path("id") int id);

    //Shahin Edits
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("Student/Search/{q}/{f}")
    Call<ResponseSearch> Search(
            @Header("Authorization") String token,
            @Path("q") String query

    );
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("Student/Search/{q}/{f}")
    Call<ResponseSearch> Search2(
            @Header("Authorization") String token,
            @Path("q") String query,
            @Path("f") int f
    );
//followCompany
    @PUT("Student/FollowCompany/{ID}")
    Call<ResponseFollow>RESPONSE_FOLLOW_CALL(
        @Header("Authorization") String token,
        @Path("ID") int query
        );
    @DELETE("Student/UnfollowCompany/{ID}")
    Call<ResponseFollow> removeFollow(
            @Header("Authorization") String token,
            @Path("ID") int query
    );
    @PUT("skills")
    Call<ResponseSkills>RESPONSE_SKILLS_CALL(
            @Header("Authorization") String token
    );
}
