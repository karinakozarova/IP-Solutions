regExps = {
"exercise_1": /[A-Z]+[a-z]+/,
"exercise_2": /088[1-9]{7}/,
"exercise_3": /[^0-1]{1,}/,
"exercise_4": /^[a-zA-Z0-9\._-]{3,15}$/,
"exercise_5": /^(1[0-4]?[0-9][0-9]|1500|^[0-9]{1,3})$/,
"exercise_6": /class=(\"|\')(.*)+(\"|\')/
};
cssSelectors = {
"exercise_1": "css > item > java",
"exercise_2": "different > different > java",
"exercise_3": "item > java > tag",
"exercise_4": "css > item:nth-last-of-type(1)",
"exercise_5": "css > item > tag > java:nth-last-of-type(2)",
"exercise_6": "css > item >item > item > item:nth-last-of-type(1) > item",
"exercise_7": "different > different:nth-last-of-type(1)>java:nth-last-of-type(1)",
"exercise_8": "#someId"
};
