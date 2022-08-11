package com.example.demo.docs.descriptor.impl

import com.example.demo.docs.descriptor.DescriptorCollector
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation

class DemoDescriptorCollector : DescriptorCollector {

    companion object {
        fun getFieldDescriptor() = DescriptorCollector.fieldDescriptor(
            DemoDescriptor.getNameList()
        )

        fun commonFieldDescriptors(commonMap: Map<String, String>): List<FieldDescriptor> {
            return commonMap.map {
                PayloadDocumentation.fieldWithPath(it.key).description(it.value)
            }.toList()
        }
    }
}
