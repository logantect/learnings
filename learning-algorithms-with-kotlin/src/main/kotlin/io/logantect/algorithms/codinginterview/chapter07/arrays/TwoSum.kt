package io.logantect.algorithms.codinginterview.chapter07.arrays

interface TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray
}

class BruteForceTwoSum : TwoSum {
    override fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        throw IllegalArgumentException("No two sum solution")
    }
}