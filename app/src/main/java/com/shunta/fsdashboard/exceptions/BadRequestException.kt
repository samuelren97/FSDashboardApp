package com.shunta.fsdashboard.exceptions

class BadRequestException(message: String): Exception(message) {
    override fun toString(): String {
        return "BadRequestException(message=$message)"
    }
}