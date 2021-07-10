function total() {

    return value +
}

// value:
//     read: plugins\\RPGLib\\scripts\\type.js::valuePattern
// percent:
//     read: plugins\\RPGLib\\scripts\\type.js::percentPattern
// max_min:
//     read: plugins\\RPGLib\\scripts\\type.js::max_minPattern
// percent_max_min:
//     read: plugins\\RPGLib\\scripts\\type.js::percent_max_minPattern

function attributeTotal(attributeName, fixedData) {
    var status = attData.get(attributeName)
    var value = status.get('value')
    var percent = status.get('percent')
    var value_min = status.get('value_min')
    var value_max = status.get('value_max')
    var percent_max = status.get('percent_max')
    var percent_min = status.get('percent_min')

    function random_max_min(min, max) {
        var NumberUtils = Packages.com.skillw.rpglib.util.NumberUtils
        return NumberUtils.getRandom(min, max, fixedData)
    }

    return (value + random_max_min(value_min, value_max)) * (1 + ( percent + random_max_min(percent_min, percent_max)))
}