package com.example.demo.docs.descriptor

import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.hypermedia.HypermediaDocumentation
import org.springframework.restdocs.hypermedia.LinkDescriptor
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestPartDescriptor

interface DescriptorCollector {

    companion object {
        fun headerDescriptor(list: List<Descriptor>): List<HeaderDescriptor> {
            return list.map {
                HeaderDocumentation.headerWithName(it.path).description(it.description)
            }.toList()
        }

        fun linkDescriptor(list: List<Descriptor>): List<LinkDescriptor> {
            return list.map {
                HypermediaDocumentation.linkWithRel(it.path).description(it.description)
            }.toList()
        }

        fun fieldDescriptor(list: List<Descriptor>): List<FieldDescriptor> {
            return list.map {
                if (!it.optional) {
                    PayloadDocumentation.fieldWithPath(it.path).type(it.type).description(it.description)
                } else {
                    PayloadDocumentation.fieldWithPath(it.path).type(it.type).description(it.description).optional()
                }
            }.toList()
        }

        fun requestPartDescriptor(list: List<Descriptor>): List<RequestPartDescriptor> {
            return list.map {
                RequestDocumentation.partWithName(it.path).description(it.description)
            }.toList()
        }

        fun parameterDescriptor(list: List<Descriptor>): List<ParameterDescriptor> {
            return list.map {
                RequestDocumentation.parameterWithName(it.path).description(it.description)
            }.toList()
        }
    }
}
