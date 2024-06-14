<template>
  <div class="contact_area wrap">
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-sm-6 col-xs-12">
          <div class="main_contact">
            <h1>SignUp</h1>
            <p class="welcome-message">Welcome to sign up!</p>
            <p>Please fill in the form to create an account.</p>
          </div>
        </div>
        <div class="col-md-8 col-sm-6 col-xs-12">
          <div class="main_contact2">
            <form @submit.prevent="submit">
              <div class="col-sm-6 col-md-6 col-xs-12">
                <label for="exampleInputname1" class="form-label"
                  >User ID:</label
                >
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control"
                    id="exampleInputname1"
                    placeholder="아이디 입력"
                    v-model="id"
                  />
                  <button
                    class="btn btn-primary"
                    type="button"
                    @click="idCheck"
                  >
                    Check Availability
                  </button>
                </div>
                <p
                  :class="{
                    success: idCheckStatus === 'success',
                    error: idCheckStatus === 'error',
                  }"
                  id="idCheck-msg"
                >
                  {{ idCheckMessage }}
                </p>
              </div>

              <div class="col-sm-6 col-md-6 col-xs-12">
                <label for="exampleInputPassword1" class="form-label"
                  >Password:</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="exampleInputPassword1"
                  v-model="pwd"
                />
              </div>

              <div class="col-sm-6 col-md-6 col-xs-12">
                <label for="exampleInputEmail1" class="form-label"
                  >Email:</label
                >
                <input
                  type="email"
                  class="form-control"
                  id="exampleInputEmail1"
                  v-model="email"
                />
              </div>

              <div class="col-sm-12 col-md-12">
                <button class="btn btn-success w-100" type="submit">
                  Sign Up
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      id: "",
      pwd: "",
      name: "",
      email: "",
      idCheckStatus: "", // 추가된 상태 변수
      idCheckMessage: "", // 추가된 메시지 변수
    };
  },
  methods: {
    idCheck() {
      if (this.id === "") {
        this.idCheckMessage = "아이디는 필수 입력 사항입니다.";
        this.idCheckStatus = "error";
        return false;
      }
      axios
        .get(`http://192.168.0.39/thismovie0607/uboard/idchk/${this.id}`)
        .then((res) => {
          if (res.data === 0) {
            this.idCheckMessage = "사용 가능한 아이디입니다.";
            this.idCheckStatus = "success";
          } else {
            this.idCheckMessage = "이미 사용중인 아이디입니다.";
            this.idCheckStatus = "error";
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    submit() {
      const memberData = {
        id: this.id,
        pwd: this.pwd,
        name: this.name,
        email: this.email,
      };
      axios
        .post(
          `http://192.168.0.222:80/thismovie0607/uboard/signup`,
          memberData
        )
        .then((res) => {
          alert("회원가입이 완료되었습니다.");
          this.$router.push("/");
        })
        .catch((error) => {
          alert("회원가입 중 오류가 발생했습니다.");
          console.error(error);
        });
    },
  },
};
</script>

<style scoped>
input[type="text"],
input[type="password"],
input[type="email"] {
  font-size: 16px; /* 글자 크기를 16px로 설정 */
  padding: 10px; /* 입력 필드 내부의 여백을 늘립니다 */
  width: 100%; /* 필드의 폭을 부모 요소에 맞춥니다 */
  font-weight: bold; /* 글자를 진하게 만듭니다 */
}

#idCheck-msg.success {
  color: green;
}
#idCheck-msg.error {
  color: red;
}

.main_contact p {
  font-family: 'cursive'; /* 필기체 글꼴 적용 */
  font-size: 18px; /* 글자 크기를 적당히 조절 */
  color: #333; /* 글자 색상 설정 */
}
.welcome-message {
  font-family: 'Dancing Script', cursive !important; /* 필기체 글꼴 적용, 강제 적용 */
  font-size: 24px !important; /* 글자 크기 조절, 강제 적용 */
  color: #da6a0f !important; /* 색상 변경, 강제 적용 */
  font-weight: bold !important; /* 글자 두껍게, 강제 적용 */
}

</style>

