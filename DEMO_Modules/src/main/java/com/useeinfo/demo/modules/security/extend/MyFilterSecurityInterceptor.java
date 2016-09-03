package com.useeinfo.demo.modules.security.extend;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤用户请求
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	/**
	 * 与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，
	 * 其他的两个组件，已经在AbstractSecurityInterceptor定义
	 */
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	/**
	 * Indicates the type of secure objects the subclass will be presenting to
	 * the abstract parent for processing. This is used to ensure collaborators
	 * wired to the {@code AbstractSecurityInterceptor} all support the
	 * indicated secure object class.
	 *
	 * @return the type of secure object the subclass provides services for
	 */
	@Override
	public Class<?> getSecureObjectClass() {
		//下面的MyAccessDecisionManager的supports方面必须返回true,否则会提醒类型错误
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	/**
	 * The <code>doFilter</code> method of the Filter is called by the
	 * container each time a request/response pair is passed through the
	 * chain due to a client request for a resource at the end of the chain.
	 * The FilterChain passed in to this method allows the Filter to pass
	 * on the request and response to the next entity in the chain.
	 * <p/>
	 * <p>A typical implementation of this method would follow the following
	 * pattern:
	 * <ol>
	 * <li>Examine the request
	 * <li>Optionally wrap the request object with a custom implementation to
	 * filter content or headers for input filtering
	 * <li>Optionally wrap the response object with a custom implementation to
	 * filter content or headers for output filtering
	 * <li>
	 * <ul>
	 * <li><strong>Either</strong> invoke the next entity in the chain
	 * using the FilterChain object
	 * (<code>chain.doFilter()</code>),
	 * <li><strong>or</strong> not pass on the request/response pair to
	 * the next entity in the filter chain to
	 * block the request processing
	 * </ul>
	 * <li>Directly set headers on the response after invocation of the
	 * next entity in the filter chain.
	 * </ol>
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	/**
	 * 最核心的代码就是invoke方法中的InterceptorStatusToken token = super.beforeInvocation(fi);这一句
	 * 即在执行doFilter之前，进行权限的检查，而具体的实现已经交给accessDecisionManager了
	 */
	private void invoke(FilterInvocation fi) throws IOException, ServletException {
		// object为FilterInvocation对象
		// super.beforeInvocation(fi);//源码
		// 1.获取请求资源的权限
		//执行 Collection<ConfigAttribute> attributes =
		//securityMetadataSource.getAttributes(fi);
		// 2.是否拥有权限
		// this.accessDecisionManager.decide(authenticated, fi, attributes);
		// this.accessDecisionManager.decide(authenticated, fi, attributes);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
}
