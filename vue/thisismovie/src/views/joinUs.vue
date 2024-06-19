<template>
    <div>
        <br>
        <h1>회원가입</h1>
        <form @submit.prevent="submit">
            <!--<form>-->
            <table>
                <tr class="onetr">
                    <th class="labelName">ID</th>
                    <th>:</th>
                    <td><label for="id" class="form-label">
                    <input type="id" class="form-control" id="id" name="id" placeholder="4~20자리 / 영문,숫자,특수문자'_'사용가능">
                </label>
                    </td>
                    <td><button class="btn btn-primary" type="button" @click="idCheck">중복확인</button>
                    </td>
                </tr>

                <tr>
                    <td colspan="4">
                        <p style="display:none" id="idCheck-msg" class="text"></p>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName">Password</th>
                    <th>:</th>
                    <td colspan="2"><label for="pwd" class="form-label">
                        <input type="password" class="form-control" id="pwd" name="pwd" placeholder="8~16자리/영문 대소문자,숫자,특수문자 조합">
                        </label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName">이름</th>
                    <th>:</th>
                    <td colspan="2"><label for="name" class="form-label">
                        <input type="text" class="form-control" id="name" name="name" placeholder="이름 입력">
                        </label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName">키</th>
                    <th>:</th>
                    <td colspan="2"><label for="name" class="form-label">
                        <input type="number" class="form-control" id="height" name="height" placeholder="키를 입력"
                                v-model="height">
                        </label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName">몸무게</th>
                    <th>:</th>
                    <td colspan="2"><label for="name" class="form-label">
                        <input type="number" class="form-control" id="weight" name="weight" placeholder="몸무게를 입력"
                            v-model="weight">
                        </label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName" colspan="4">
                        <button type="button" 
                        @click="bmicheck">
                        bmi 확인</button>
                    </th>
                </tr>

                <tr class="onetr">
                    <th class="labelName">생년월일</th>
                    <th>:</th>
                    <td colspan="2"><label for="age" class="form-label">
                        <input type="text" class="form-control" id="age" name="age" placeholder="YYYYMMDD">
                        </label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName">성별</th>
                    <th>:</th>
                    <td colspan="2"><input class="form-check-input" type="radio" name="gender" id="gender" value="남자">
                        <label class="form-check-label" for="inlineRadio1">남자</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input class="form-check-input" type="radio" name="gender" id="gender" value="여자">
                        <label class="form-check-label" for="inlineRadio2">여자</label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName">휴대폰</th>
                    <th>:</th>
                    <td colspan="2"><label for="phone" class="form-label">
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="'_'빼고 숫자만 입력">
                        </label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th class="labelName">이메일</th>
                    <th>:</th>
                    <td colspan="2"><label for="email" class="form-label">
                        <input type="text" class="form-control" id="email" name="email" placeholder="email@ict.co.kr">
                        </label>
                    </td>
                </tr>

                <tr class="onetr">
                    <th>
                        이미지 업로드
                    </th>
                    <th>:</th>
                    <td colspan="2">
                        <input type="file" 
                        @change="selectFile" 
                        multiple accept="image/*" 
                        ref="fileRef" id="fileRef" />
                    </td>
                </tr>

                <tr>
                    <td colspan="4">
                        <div class="images" v-if="files.length > 0">
                        {{ files[3] }}
                        <div v-for="file in files" :key="file.name" class="image">
                        <img :src="file.preview" alt="이미지" >
                        </div>
                    </div>
                    </td>
                </tr>

                <tr class="onetr">
                    <td colspan="4">
                        <button type="submit" 
                        class="btn btn-primary" >
                        회원가입</button>
                    </td>
                </tr>
            </table>
        </form>
        <br>
    <!-- <h2>당신의 키 {{this.height}}</h2>
    <h2>당신의 몸무게{{this.weight}}</h2>
    <h2>당신의 Bmi 수치{{this.bmi}}</h2>
    <h2>당신의 Bmi 결과{{this.pred}}</h2>
        <div id="chart" style="margin:auto; width:50%;">
      <highcharts :options="chartOptions"></highcharts> -->
    <!-- </div> -->

    </div>
</template>

<script>
// import Highcharts from 'highcharts';
import axios from 'axios';
import {ref} from 'vue';
export default {
    data(){
        return{
            msg:"signUp",
            id: "", 
            pwd: "",
            name: "",
            age: "",
            gender: "",
            phone: "",
            email: "",
            reip: "",
            memdate: "",
            files: [],
            height: 0,
            weight: 0,
            pred:"",
            bmi:0,
        // chartOptions: {
        // chart: {
        //   type: 'gauge',
        //   plotBackgroundColor: null,
        //   plotBackgroundImage: null,
        //   plotBorderWidth: 0,
        //   plotShadow: false,
        //   height: '80%'
        // },
        // title: {
        //   text: 'BMI'
        // },
        // pane: {
        //   startAngle: -90,
        //   endAngle: 89.9,
        //   background: null,
        //   center: ['50%', '75%'],
        //   size: '110%'
        // },
        // yAxis: {
        //   min: 10,
        //   max: 40,
        //   tickPixelInterval: 40,
        //   tickPosition: null,
        //   tickColor: Highcharts.defaultOptions.chart.backgroundColor || '#FFFFFF',
        //   tickLength: 20,
        //   tickWidth: 2,
        //   minorTickInterval: null,
        //   labels: {
        //     distance: 20,
        //     style: {
        //       fontSize: '14px'
        //     }
        //   },
        //   lineWidth: 0,
        //   plotBands: [
        //     {
        //       from: 10,
        //       to: 18.4,
        //       color: '#FAED7D',
        //       thickness: 20,
        //       borderRadius: '50%'
        //     },
        //     {
        //       from: 18.5,
        //       to: 23,
        //       color: '#47C83E',
        //       thickness: 20,
        //       borderRadius: '50%'
        //     },
        //     {
        //       from: 23,
        //       to: 30,
        //       color: '#F361DC',
        //       thickness: 20,
        //       borderRadius: '50%'
        //     },
        //     {
        //       from: 30,
        //       to: 35,
        //       color: '#F15F5F',
        //       thickness: 20,
        //       borderRadius: '50%'
        //     },
        //     {
        //       from: 35,
        //       to: 60,
        //       color: '#FF0000',
        //       thickness: 20,
        //       borderRadius: '50%'
        //     }
        //   ]
        // },
        // series: [
        //   {
        //     name: 'bmi',
        //     // 기본 bmi지수 값
        //     data: [10],
        //     tooltip: {
        //       valueSuffix: '점'
        //     },
        //     dataLabels: {
        //     // 
        //     format: '{y} 지수',
        //     borderWidth: 0,
        //     color: (
        //         Highcharts.defaultOptions.title &&
        //         Highcharts.defaultOptions.title.style &&
        //         Highcharts.defaultOptions.title.style.color
        //     ) || '#333333',
        //     style: {
        //         fontSize: '16px'
        //     }
        // },
        //     dial: {
        //       radius: '80%',
        //       backgroundColor: 'gray',
        //       baseWidth: 12,
        //       baseLength: '0%',
        //       rearLength: '0%'
        //     },
        //     pivot: {
        //       backgroundColor: 'gray',
        //       radius: 6
        //     }
        //   },
        // ]
    //   },
        }
    },
    computed: {
      backendUrl() {
        //return process.env.VUE_APP_BACKEND_URL;
        return "http://192.168.0.39/thismovie0607"
      },
    },
    methods: {
        // id가 idCheck 인부분
        idCheck(){
            // 작성된 아이디를 userId 변수에 저장
            let userId = this.id;
            userId = document.getElementById("id").value;
            console.log(userId);
            // idcheck-msg 값을 가져옴
            const idCheckMsg = document.getElementById("idCheck-msg")
            // if문으로 아이디 중복여부 확인
            if(userId === ""){ // 아이디 입력이 안됐을 때
                idCheckMsg.innerText = "아이디는 필수 입력 사항입니다.";
                idCheckMsg.style.display = "block"; // display요소를 block으로 변경
                // focus로 다시 아이디를 바로 입력할 수 있게 함.
                document.getElementById("id").focus();
                // false로 form 제출 못하게 함.
                return false;
            }else{
                // 입력되어있으면 block요소 제거
                idCheckMsg.style.display = "none";
            }
            axios.post(`http://192.168.0.39/thismovie0607/newmember/idcheck`,{
                id: userId
            })
                 .then((res)=>{
                console.log(res) // 존재하면1, 사용가능 0
                if(res.data === 0){
                    idCheckMsg.innerText = "사용가능한 아이디 입니다.";
                    idCheckMsg.style.color = "green";
                }else{
                    idCheckMsg.innerText = "이미 사용중인 아이디 입니다.";
                    idCheckMsg.style.color = "red";
                }
                idCheckMsg.style.display = "block"
            })
                 .catch((err)=>{})
        },
        selectFile(event) {
        // 파일 선택시 초기화 해줘야 선택한 파일만 files 에 들어감
        this.files = [];
        // 폼으로 데이터를 전송할 객체
        const formData = new FormData();
        // 이벤트 발생한 파일을 받아줌
        const selectedFiles = event.target.files;
        console.log('확인im',selectedFiles);
        // 받아준 파일의 정보를 뽑아냄
        for (let i = 0; i < selectedFiles.length; i++) {
          // 뽑아낸 파일의 정보를 배열로 하나씩 뽑아서 file에 저장
          const file = selectedFiles[i];
          // 미리보기 URL 생성
          const preview = URL.createObjectURL(file);
          // 파일 정보와 미리보기 URL을 files 배열에 추가
          this.files.push({
            // 이름은 name에 저장
            name: file.name,
            // 미리보기는 preview에 저장
            preview: preview,
          });
        // file에 저장된 값을 formData에 저장
        formData.append('file', file);
        }
      },
      fetchFiles() {
        console.log('확인2');
      },
      submit(event){
        console.log(event);
            // 파일 선택시 초기화 해줘야 선택한 파일만 files 에 들어 가기 때문이다.
            this.files = [];
            // 폼으로 데이터를 전송할 객체
            const formData_f = new FormData();
            const selectedFiles_f = event.target.fileRef.files;
            
            for (let i = 0; i < selectedFiles_f.length; i++) {
                const file_f = selectedFiles_f[i];
                // 미리보기 URL 생성
                const preview_f = URL.createObjectURL(file_f);
                // 파일 정보와 미리보기 URL을 files 배열에 추가
                this.files.push({
                    name: file_f.name,
                    preview: preview_f,
                });
            // 업로드할 파일은 FormData에 추가
            //formData.append('files', file);
            formData_f.append('file', file_f);
            formData_f.append('id', document.getElementById("id").value);
            formData_f.append('pwd', document.getElementById("pwd").value);
            formData_f.append('name', document.getElementById("name").value);
            formData_f.append('age', document.getElementById("age").value);
            formData_f.append('gender', document.getElementById("gender").value);
            formData_f.append('phone', document.getElementById("phone").value);
            formData_f.append('email', document.getElementById("email").value);
            formData_f.append('height', this.height);
            formData_f.append('weight', this.weight);
            formData_f.append('bmi', 0);
            formData_f.append('pred', 0);
            }
            axios.post('http://192.168.0.39/thismovie0607/newmember/newmemberAdd',formData_f, {
            headers: { 'Content-Type': 'multipart/form-data' },
            }).then((res)=>{
                console.log(res);
                alert('회원가입이 완료되었습니다.');
                this.$router.push("/");
            })
                 .catch((error)=>{
                console.log(error);
                alert('회원가입 중 오류가 발생했습니다.');
            });
        },
    //   bmicheck(){
    //         // 키와 몸무게 값을 저장할 객체 생성
    //         const formData = new FormData();
    //         // 입력받은 키와 몸무게 값을 append로 formData 저장
    //         formData.append('height',this.height);
    //         formData.append('weight', this.weight);
    //         // 입력받은 값을 bmi예측을 위해 Django로 전달
    //         axios.post('http://192.168.0.130:9000/bmi/bmichart',formData, 
    //         // Django에서 예측 후 다시 Vue로 전달됨.
    //         ).then((res)=>{
    //             // 잘 넘어왔는지 확인
    //             console.log(res.data["mh"]);
    //             console.log(res.data['mw']);
    //             console.log(res.data["pred"]);
    //             console.log(res.data["bmi"]);
    //             // BMI 값을 데이터에 저장
    //             this.bmi = res.data["bmi"];     
    //             this.pred = res.data["pred"];
    //             // 하이차트 용
    //             this.chartOptions.series[0].data = [this.bmi];
    //         }).catch((error)=>{
    //             console.log(error);
    //             alert('오류가 발생했습니다.');
    //         });
    //     }
    },
    
}
</script>




<style scoped>
table{
    margin: auto;
    width: 65px;
}
.labelName{
    text-align: center;
}
.form-control{
    width: 400px;
    padding-left: auto;
}
.onetr th, .onetr td {
    padding-top: 30px;
}
.images img { width: 100px;}
</style>
