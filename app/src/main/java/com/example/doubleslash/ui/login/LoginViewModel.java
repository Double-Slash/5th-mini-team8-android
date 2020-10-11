package com.example.doubleslash.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.example.doubleslash.RequestHttpURLConnection_POST;
import com.example.doubleslash.data.LoginRepository;
import com.example.doubleslash.data.Result;
import com.example.doubleslash.data.model.LoggedInUser;
import com.example.doubleslash.R;

import org.json.JSONException;
import org.json.JSONObject;

//서버통신
public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private final static String Tag = "LoginViewModel";
    Result<LoggedInUser> result;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String user_id, String password) {

        String url = "https://0c73c962765a.ngrok.io/user/login";
        JSONObject user_json = new JSONObject();

        // 로그인할 id와 password를 json으로 파싱하여 전송데이터 설정
        try {
            user_json.accumulate("user_id", user_id);
            user_json.accumulate("password", password);
            Log.e(Tag,user_id+", "+password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 로그인 API url과 로그인 정보가 저장된 데이터로 POST 방식 NetworkTask_POST 호출
        NetworkTask_POST networkTask_POST = new NetworkTask_POST(url, user_json.toString());
        networkTask_POST.execute();
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } /*else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }*/
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    /*private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }*/

    public class NetworkTask_POST extends AsyncTask<Void, Void, String> {

        private String url;
        private String json;

        public NetworkTask_POST(String url, String json) {
            this.url = url;
            this.json = json;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection_POST requestHttpURLConnection = new RequestHttpURLConnection_POST();
            result = requestHttpURLConnection.request(url, json); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        //HTTP 통신 결과처리되는 부분
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // 로그인 API의 결과로 성공시 토큰 값, 실패시 Null값을 전달받음
            // 결과값은 s에 저장

            Log.e(Tag,"h: "+s);

            //로그인 성공
            if (!TextUtils.isEmpty(s)) {
                try {
                    // 결과값으로 넘어온 JWT 토큰 JSON값 파싱
                    JSONObject result_json = new JSONObject(s);
                    JSONObject jwt_json = result_json.getJSONObject("data");
                    String accessToken = jwt_json.getString("accessToken");
                    String refreshToken = jwt_json.getString("refreshToken");
                    Log.e(Tag,refreshToken);

                    //date.getnickname 아직 null값
                    result = loginRepository.login("aaa", "aaa");
                    LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
                    loginResult.setValue(new LoginResult(new LoggedInUserView(data.getUserId())));
//                    LoggedInUser loggedInUser = LoggedInUser.getLoggedInUser();

                    Log.e(Tag,"로그인 성공");
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            //로그인 실패
            else {
                loginResult.setValue(new LoginResult(1));
            }
        }
    }


}