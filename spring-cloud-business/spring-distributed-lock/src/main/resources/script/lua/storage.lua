local type_err_msg = "{\"code\":\"0x10001\",\"message\":\"value type error: not string,either not number\",\"data\":\"\"}"
local key_err_msg = "{\"code\":\"0x10001\",\"message\":\"key not exist\",\"data\":\"\"}"

if (redis.call('exists', KEYS[1]) == 1) then

    -- 对 redis中现有的库存值进行类型转换  start --
    local current_storage_num = redis.call("get", KEYS[1])
    if type(current_storage_num) =="string" then
        current_storage_num = current_storage_num:gsub("^%s*\"*%s*(%d+)%s*\"*%s*$","%1")
        current_storage_num = tonumber(current_storage_num)
    else
        if type(current_storage_num) ~="number" then
            return type_err_msg
        end
    end

    if current_storage_num == nil then
        return type_err_msg
    end
    -- 对 redis中现有的库存值进行类型转换  end --


    -- 对 传入的要扣减的库存数进行类型转换 start --
    local decrease_num=  ARGV[1]
    if type(decrease_num) =="string" then
        decrease_num = decrease_num:gsub("^%s*\"*%s*(%d+)%s*\"*%s*$","%1")
        decrease_num = tonumber(decrease_num)
    else
        if type(decrease_num) ~="number" then
            return type_err_msg
        end
    end

    if decrease_num == nil then
        return type_err_msg
    end
    -- 对 传入的要扣减的库存数进行类型转换 end --

    -- 进行库存计算 --
    local new_storage_num = current_storage_num - decrease_num
    if new_storage_num >= 0 then
        redis.call("set", KEYS[1], tostring(new_storage_num))
      return  "{\"code\":\"0\",\"message\":\"success\",\"data\":"..new_storage_num.."}"
    else
      return "{\"code\":\"0x20001\",\"message\":\"storage not enough\",\"data\":\"\"}"
    end
else
    return key_err_msg
end



--function convertToNumber(value)
--    local returnVaule
--    if type(value) =="string" then
--        returnVaule = tonumber(trim(value))
--    else
--        if type(value) ~="number" then
--            return type_err_msg
--        end
--    end
--
--    if returnVaule == nil then
--        return type_err_msg
--    end
--
--    return returnVaule
--end
--
--function trim(s)
--    return (s:gsub("^%s*(.-)%s*$", "%1"))
--end