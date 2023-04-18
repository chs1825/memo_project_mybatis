function jsonToParam(jsonData) {
    if (!jsonData || typeof jsonData !== 'object') return jsonData;
    const paramArr = [];
    for (const key in jsonData) {
        const value = jsonData[key];
        if (typeof value === 'string' || typeof value === 'number') {
            if (value) {
                paramArr.push(key+"="+encodeURIComponent(value));
            }
        } else if (Array.isArray(value)) {
            for (let i = 0; i < value.length; i++) {
                const subValue = value[i];
                if (subValue) {
                    paramArr.push(key+"="+encodeURIComponent(subValue));
                }
            }
        } else if (typeof value === 'object' && value !== null) {
            const subParam = jsonToParam(value);
            if (subParam) {
                paramArr.push(key+"."+subParam);
            }
        }
    }
    return paramArr.join('&').trim();

}
