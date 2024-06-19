<template>
  <div class="container mt-3" id="app">
    <h1 class="display-1 text-center">회원님들</h1>
    <div class="btn-group">
      <router-link to="/user/save" class="btn btn-primary">회원님 추가</router-link>
    </div>
    <div id="chart">
      <!-- <highcharts :options="chartOptions"></highcharts> -->
    </div>
  </div>
</template>

<script>
// import Highcharts from 'highcharts';

// export default {
//   name: 'App',
//   data() {
//     // return {
//       // chartOptions: {
//       //   chart: {
//       //     type: 'gauge',
//       //     plotBackgroundColor: null,
//       //     plotBackgroundImage: null,
//       //     plotBorderWidth: 0,
//       //     plotShadow: false,
//       //     height: '80%'
//       //   },
//       //   title: {
//       //     text: 'BMI'
//       //   },
//       //   pane: {
//       //     startAngle: -90,
//       //     endAngle: 89.9,
//       //     background: null,
//       //     center: ['50%', '75%'],
//       //     size: '110%'
//       //   },
//       //   yAxis: {
//       //     min: 10,
//       //     max: 40,
//       //     tickPixelInterval: 40,
//       //     tickPosition: null,
//       //     tickColor: Highcharts.defaultOptions.chart.backgroundColor || '#FFFFFF',
//       //     tickLength: 20,
//       //     tickWidth: 2,
//       //     minorTickInterval: null,
//       //     labels: {
//       //       distance: 20,
//       //       style: {
//       //         fontSize: '14px'
//       //       }
//       //     },
//       //     lineWidth: 0,
//           // plotBands: [
//           //   {
//           //     from: 10,
//           //     to: 18.4,
//           //     color: '#FAED7D',
//           //     thickness: 20,
//           //     borderRadius: '50%'
//           //   },
//           //   {
//           //     from: 18.5,
//           //     to: 23,
//           //     color: '#47C83E',
//           //     thickness: 20,
//           //     borderRadius: '50%'
//           //   },
//           //   {
//           //     from: 23,
//           //     to: 30,
//           //     color: '#F361DC',
//           //     thickness: 20,
//           //     borderRadius: '50%'
//           //   },
//           //   {
//           //     from: 30,
//           //     to: 35,
//           //     color: '#F15F5F',
//           //     thickness: 20,
//           //     borderRadius: '50%'
//           //   },
//           //   {
//           //     from: 35,
//           //     to: 60,
//           //     color: '#FF0000',
//           //     thickness: 20,
//           //     borderRadius: '50%'
//           //   }
//           // ]
//         },
//         // series: [
//         //   {
//         //     name: 'Speed',
//         //     data: [20],
//         //     tooltip: {
//         //       valueSuffix: '점'
//         //     },
//         //     dataLabels: {
//         //       format: '{y} 지수',
//         //       borderWidth: 0,
//         //       color:
//         //         (Highcharts.defaultOptions.title &&
//         //           Highcharts.defaultOptions.title.style &&
//         //           Highcharts.defaultOptions.title.style.color) ||
//         //         '#333333',
//         //       style: {
//         //         fontSize: '16px'
//         //       }
//         //     },
//         //     dial: {
//         //       radius: '80%',
//         //       backgroundColor: 'gray',
//         //       baseWidth: 12,
//         //       baseLength: '0%',
//         //       rearLength: '0%'
//         //     },
//         //     pivot: {
//         //       backgroundColor: 'gray',
//         //       radius: 6
//         //     }
//         //   }
//         // ]
//       // }
//     // };
//   }
// };
// </script>

<style>
.container {
  margin-top: 3rem;
}
</style>
