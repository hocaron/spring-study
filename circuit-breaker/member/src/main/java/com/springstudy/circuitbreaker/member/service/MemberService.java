package com.springstudy.circuitbreaker.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springstudy.circuitbreaker.member.client.PointFeignClient;
import com.springstudy.circuitbreaker.member.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PointFeignClient pointFeignClient;

	@Transactional
	public void getMemberInfo(long id) {

		memberRepository.findById(id)
			.orElseThrow();

		pointFeignClient.getPoints("READ_TIME_OUT");
	}
}
