// 各类型新能源上牌数 chart1
(function () {
  var myChart = echarts.init(document.querySelector(".pie .chart"));
  // var pieData = [
  //   { name: 'MPV', value: 8031 },
  //   { name: 'SUV', value: 174928 },
  //   { name: '大型车', value: 342 },
  //   { name: '紧凑型车', value: 55595 },
  //   { name: '微型车', value: 1001 },
  //   { name: '小型车', value: 11617 },
  //   { name: '中大型车', value: 26715 },
  //   { name: '中型车', value: 29224 },
  // ]
  var pieData = [];
  $.ajax({
    url: 'http://localhost/moviechart1',  // 替换 'your_data_url' 为实际的数据请求地址
    method: 'GET',
    dataType: 'json',
    success: function (data) {
      // 在这里处理获取到的数据，例如更新 pieData
      // 示例：假设数据的格式为 [{ name: 'Category1', value: 100 }, { name: 'Category2', value: 200 }, ...]
      pieData = data;

      // 更新图表配置
      myChart.setOption({
        legend: {
          data: pieData.map(item => item.name),
        },
        series: [
          {
            data: pieData,
          },
          // 其他 series 的更新...
        ],
      });
    },
    error: function (xhr, status, error) {
      console.error('Error fetching data:', error);
    }
  });
  option = {
    color: [
      "#A0CE3A",
      "#31C5C0",
      "#1E9BD1",
      "#0F347B",
      "#585247",
      "#7F6AAD",
      "#009D85",
      "rgba(250,250,250,0.3)",
    ],
    legend: {
      orient: "vertical",
      top: "middle",
      right: "3%",
      textStyle: {
        color: "#f2f2f2",
        fontSize: transformFontSize(12),
      },
      icon: "roundRect",
      data: pieData,
    },
    tooltip: {
      show: true,
      valueFormatter: (value) => value + '个'
    },
    series: [
      // 主要展示层的
      {
        radius: ["30%", "65%"],
        center: ["37%", "50%"],
        type: "pie",
        label: {
          normal: {
            show: false,
            formatter: "{c}个",
            textStyle: {
              color: "rgba(250,250,250)",
              fontSize: transformFontSize(12),
            },
            position: "outside",
          },
          emphasis: {
            show: false,
          },
        },
        labelLine: {
          normal: {
            show: false,
            length: transformFontSize(5),
            length2: 0
          },
          emphasis: {
            show: true,
          },
        },
        name: "上牌数",
        data: pieData,
      },
      // 边框的设置
      {
        radius: ["30%", "34%"],
        center: ["37%", "50%"],
        type: "pie",
        label: {
          normal: {
            show: false,
          },
          emphasis: {
            show: false,
          },
        },
        labelLine: {
          normal: {
            show: false,
          },
          emphasis: {
            show: false,
          },
        },
        animation: false,
        tooltip: {
          show: false,
        },
        data: [
          {
            value: 1,
            itemStyle: {
              color: "rgba(250,250,250,0.3)",
            },
          },
        ],
      },
      {
        name: "外边框",
        type: "pie",
        clockWise: false, //顺时加载
        hoverAnimation: false, //鼠标移入变大
        center: ["37%", "50%"],
        radius: ["70%", "70%"],
        label: {
          normal: {
            show: false,
          },
        },
        data: [
          {
            value: 9,
            name: "",
            itemStyle: {
              normal: {
                borderWidth: 2,
                borderColor: "#0b5263",
              },
            },
          },
        ],
      },
    ],
  };
  myChart.setOption(option);
  window.addEventListener("resize", function () {
    myChart.resize();
  });
})();

// chart5
(function () {
  var myChart = echarts.init(document.querySelector(".pie1 .chart"));
  var pieData;

  // 发起Ajax请求获取数据
  $.ajax({
    url: 'http://localhost/moviechart5',  // 替换为实际的后端API地址
    method: 'GET',
    dataType: 'json',
    success: function (data) {
      // 处理返回的数据
      pieData = data;

      // 更新图表配置
      var option = {
        color: [
          "#31C5C0",
          "#009D85",
          "#A0CE3A",
          "#7F6AAD",
          "#585247",
          "rgba(250,250,250,0.3)",
          "#1E9BD1",
          "#0F347B",
        ],
        legend: {
          orient: "vertical",
          top: "middle",
          right: "3%",
          textStyle: {
            color: "#f2f2f2",
            fontSize: transformFontSize(12),
          },
          icon: "roundRect",
          data: pieData,
        },
        tooltip: {
          show: true,
          valueFormatter: (value) => value + '个'
        },
        series: [
          // 主要展示层的
          {
            radius: ["30%", "65%"],
            center: ["37%", "50%"],
            type: "pie",
            label: {
              normal: {
                show: false,
                formatter: "{c}个",
                textStyle: {
                  color: "rgba(250,250,250)",
                  fontSize: transformFontSize(12),
                },
                position: "outside",
              },
              emphasis: {
                show: false,
              },
            },
            labelLine: {
              normal: {
                show: false,
                length: transformFontSize(5),
                length2: 0
              },
              emphasis: {
                show: true,
              },
            },
            name: "上牌数",
            data: pieData,
          },
          // 边框的设置
          {
            radius: ["30%", "34%"],
            center: ["37%", "50%"],
            type: "pie",
            label: {
              normal: {
                show: false,
              },
              emphasis: {
                show: false,
              },
            },
            labelLine: {
              normal: {
                show: false,
              },
              emphasis: {
                show: false,
              },
            },
            animation: false,
            tooltip: {
              show: false,
            },
            data: [
              {
                value: 1,
                itemStyle: {
                  color: "rgba(250,250,250,0.3)",
                },
              },
            ],
          },
          {
            name: "外边框",
            type: "pie",
            clockWise: false, //顺时加载
            hoverAnimation: false, //鼠标移入变大
            center: ["37%", "50%"],
            radius: ["70%", "70%"],
            label: {
              normal: {
                show: false,
              },
            },
            data: [
              {
                value: 9,
                name: "",
                itemStyle: {
                  normal: {
                    borderWidth: 2,
                    borderColor: "#0b5263",
                  },
                },
              },
            ],
          },
        ],
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    error: function (xhr, status, error) {
      console.error('Error fetching data:', error);
    }
  });

  window.addEventListener("resize", function () {
    myChart.resize();
  });
})();


//chart2
(function () {
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".words .chart"));
  var option = {
    series: [
      {
        name: "热点分析",
        type: "wordCloud",
        textPadding: 0,
        autoSize: {
          enable: true,
          minSize: 6,
        },
        textStyle: {
          normal: {
            color: function () {
              return (
                  "rgb(" +
                  [
                    Math.round(Math.random() * 105) + 150,
                    Math.round(Math.random() * 105) + 150,
                    Math.round(Math.random() * 105) + 150,
                  ].join(",") +
                  ")"
              );
            },
          },
          emphasis: {
            shadowBlur: 10,
            shadowColor: "#333",
          },
        },
        data: [],
      },
    ],
  };

  // 2. 发起 Ajax 请求获取数据
  $.ajax({
    url: 'http://localhost/moviechart2', // 替换为实际的 API 地址
    method: 'GET',
    dataType: 'json',
    success: function (data) {
      // 3. 配置项和数据给我们的实例化对象
      option.series[0].data = data;
      console.log(data)
      console.log(option)
      myChart.setOption(option);
    },
    error: function (error) {
      console.error('Error fetching data:', error);
    }
  });

  // 4. 当我们浏览器缩放的时候，图表也等比例缩放
  window.addEventListener("resize", function () {
    // 让我们的图表调用 resize 这个方法
    myChart.resize();
  });
})();

// 新能源汽车销量前10 chart4
(function () {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.querySelector(".bar .chart"));
  xData = [];
  yData = [];
  $.ajax({
    url: 'http://localhost/moviechart4',  // 替换为实际的后端API地址
    method: 'GET',
    dataType: 'json',
    success: function (data) {
      // 处理返回的数据
      xData = data.xdata;
      yData = data.ydata;

      // 更新图表配置
      var option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        grid: {
          left: 0,
          top: 15,
          right: 40,
          bottom: -15,
          containLabel: true
        },
        xAxis: {
          show: false,
        },
        yAxis: [
          {
            inverse: true,
            axisTick: "none",
            axisLine: "none",
            axisLabel: {
              textStyle: {
                color: "#81E7ED",
              },
            },
            data: xData,
          },
        ],
        series: [
          {
            name: "点击次数",
            type: "bar",
            data: yData,
            label: {
              normal: {
                show: true,
                position: "right",
                formatter: function (param) {
                  return param.value + "次";
                },
                textStyle: {
                  color: "#ffffff",
                },
              },
            },
            barWidth: transformFontSize(13),
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 1, 1, [
                {
                  offset: 0,
                  color: "#00FFE3",
                },
                {
                  offset: 1,
                  color: "#4693EC",
                },
              ]),
              barBorderRadius: [30, 30, 30, 30],
            },
          },
        ],
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    error: function (xhr, status, error) {
      console.error('Error fetching data:', error);
    }
  });


  // 使用刚指定的配置项和数据显示图表。
  window.addEventListener("resize", function () {
    myChart.resize();
  });
})();

// 新能源汽车销量后10 chart7
(function () {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.querySelector(".bar1 .chart"));
  var xData = [];
  var yData = [];

  // 发起Ajax请求获取数据
  $.ajax({
    url: 'http://localhost/moviechart7',  // 替换为实际的后端API地址
    method: 'GET',
    dataType: 'json',
    success: function (data) {
      // 处理返回的数据
      xData = data.xdata;
      yData = data.ydata;

      // 更新图表配置
      var option = {
        grid: {
          top: 20,
          left: 0,
          bottom: 0,
          right: 10,
          containLabel: true,
        },
        tooltip: {
          show: true,
        },
        animation: false,
        xAxis: [
          {
            type: "category",
            data: xData,
            axisTick: {
              alignWithLabel: true,
            },
            nameTextStyle: {
              color: "rgba(255,255,255,.5)",
              fontSize: transformFontSize(12)
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: "#82b0ec",
              },
            },
            axisLabel: {
              interval: 0,
              rotate: 25,
              textStyle: {
                color: "rgba(255,255,255,.5)",
                fontSize: transformFontSize(12)
              },
            },
          },
        ],
        yAxis: [
          {
            show: true,
            type: "value",
            axisLabel: {
              show: true,
              textStyle: {
                color: "rgba(255,255,255,.5)",
                fontSize: transformFontSize(12)
              },
            },
            splitLine: {
              lineStyle: {
                color: "#0c2c5a",
              },
            },
            axisLine: {
              show: false,
            },
          },
        ],
        series: [
          {
            type: "bar",
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "#00FFE3",
                },
                {
                  offset: 1,
                  color: "#4693EC",
                },
              ]),
              barBorderRadius: [30, 30, 30, 30],
            },
            barWidth: transformFontSize(16),
            data: yData,
          },
        ],
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    error: function (xhr, status, error) {
      console.error('Error fetching data:', error);
    }
  });

  window.addEventListener("resize", function () {
    myChart.resize();
  });
})();

// 各类别销售(上牌数)趋势 chart6
(function () {
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".map .chart"));

  // 2. 发起Ajax请求获取数据
  // 请替换以下URL为实际的数据接口地址
  var dataUrl = "http://localhost/moviechart6";
  $.ajax({
    type: "GET",
    url: dataUrl,
    dataType: "json",
    success: function (response) {
      console.log(response)
      // 3. 设置图表的option
      var option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            lineStyle: {
              color: "#dddc6b"
            }
          }
        },
        legend: {
          top: "10%",
          textStyle: {
            color: "rgba(255,255,255,.5)",
            fontSize: transformFontSize(16)
          }
        },
        grid: {
          left: "2%",
          top: "23%",
          right: "6%",
          bottom: "6%",
          containLabel: true
        },
        calculable: true,
        xAxis: {
          type: "category",
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: "#90979c",
            },
          },
          splitLine: "none",
          axisTick: "none",
          splitArea: "none",
          axisLabel: {
            interval: 0,
            fontSize: transformFontSize(16)
          },
          data: response.xdata,
        },
        yAxis: {
          type: "value",
          axisTick: { show: false },
          axisLine: {
            lineStyle: {
              color: "rgba(255,255,255,.1)"
            }
          },
          axisLabel: {
            textStyle: {
              color: "rgba(255,255,255,.6)",
              fontSize: transformFontSize(16)
            }
          },
          splitLine: {
            lineStyle: {
              color: "rgba(255,255,255,.1)"
            }
          }
        },
        series: [
          {
            name: "网站点击量",
            type: "line",
            symbol: "circle",
            symbolSize: 5,
            lineStyle: {
              normal: {
                color: "#0184d5",
                width: 2
              }
            },
            areaStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                    0,
                    0,
                    0,
                    1,
                    [
                      {
                        offset: 0,
                        color: "rgba(1, 132, 213, 0.4)"
                      },
                      {
                        offset: 0.8,
                        color: "rgba(1, 132, 213, 0.1)"
                      }
                    ],
                    false
                ),
                shadowColor: "rgba(0, 0, 0, 0.1)"
              }
            },
            itemStyle: {
              color: "#0184d5",
            },
            data: response.ydata,
          },
        ]
      };

      // 4. 配置项和数据给我们的实例化对象
      myChart.setOption(option);
    },
    error: function (error) {
      console.error("Error fetching data:", error);
    },
  });

  // 5. 当我们浏览器缩放的时候，图表也等比例缩放
  window.addEventListener("resize", function () {
    // 让我们的图表调用 resize 这个方法
    myChart.resize();
  });
})();


(function () {
  var speed = 50;
  var list = document.getElementById('list');
  var list2 = document.getElementById('list2');
  var rule = document.getElementById('rule');
  list2.innerHTML = list.innerHTML;
  function Marquee() {
    if (list2.offsetTop - rule.scrollTop <= 0)
      rule.scrollTop -= list.offsetHeight;
    else {
      rule.scrollTop++;
    }
  }
  var MyMar = setInterval(Marquee, speed);
  rule.addEventListener('mouseover', function () {
    clearInterval(MyMar)
  });
  rule.addEventListener('mouseout', function () {
    MyMar = setInterval(Marquee, speed)
  });
})();

function transformFontSize(px) {
  let clientWidth = window.innerWidth || document.body.clientWidth
  if (!clientWidth) {
    return 0
  }
  let fontSize = clientWidth / 1920
  return px * fontSize
}
