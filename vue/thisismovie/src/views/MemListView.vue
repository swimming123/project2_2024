<template>
  <div class="contact_area wrap">
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-sm-6 col-xs-12">
          <div class="main_contact">
            <h1 class="member-list-title">Member List</h1>
            <p class="happiness-message">
              "Wishing all our members abundant happiness!"
            </p>
            <div class="btn-group">
              <router-link to="/user/save" class="btn btn-primary">Add User</router-link>
            </div>
          </div>
        </div>

        <div class="col-md-8 col-sm-6 col-xs-12">
          <div class="main_contact2">
            <table class="table table-hover mt-3">
              <thead class="table-dark">
                <tr>
                  <th>Number</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Registration Date</th>
                </tr>
              </thead>
              <tbody>
                <tr class="cursor-pointer" v-for="row in list" :key="row.num" @click="href(row)">
                  <td>{{ row.num }}</td>
                  <td>{{ row.name }}</td>
                  <td>{{ row.email }}</td>
                  <td>{{ row.udate }}</td>
                </tr>
              </tbody>
              <tfoot>
                <tr><td colspan="4">{{ list }}</td></tr>
              </tfoot>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "UserList",
  created() {
    this.fetchData()
  },
  data() {
    return {
      list: []
    }
  },
  methods: {
    fetchData() {
      axios.get("http://192.168.0.39/thismovie0607/uboard/List")
        .then((resp) => {
          console.log(resp.data)
          this.list = resp.data
        })
        .catch((err) => {
          console.error(`${err} => 잘 못받았구나`)
        })
    },
    href(row) {
      this.$router.push({name: 'DetailView', params: row})
    }
  }
}
</script>

<style scoped>
.member-list-title {
  font-family: 'Dancing Script', cursive; /* 필기체 글꼴 적용 */
  font-size: 40px; /* 글자 크기 적당히 조절 */
  color: #2e9221e5; /* 글자 색상 설정 */
  font-weight: bold; /* 글자를 더욱 굵게 */
}

.happiness-message {
  font-family: 'Dancing Script', cursive; /* 필기체 글꼴 적용 */
  font-size: 24px; /* 글자 크기 적당히 조절 */
  color: #6a1b9a; /* 글자 색상 설정, 진한 자주색으로 설정 */
  font-weight: normal; /* 기본 글자 두께 */
}
</style>

<link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
