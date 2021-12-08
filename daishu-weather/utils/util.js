import CityCode from '../libs/city-code.js'
import makePy from '../libs/makePy.js' 
const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

// 通过城市获取ID
const getCity = (cityName) => {
  const _city = CityCode.find(i => {
    const regCit = new RegExp(i.cityZh)
    return regCit.test(cityName)
  })
  return {
    ..._city,
    id: _city.id.replace('CN', '')
  }
}

module.exports = {
  formatTime,
  getCity
}


