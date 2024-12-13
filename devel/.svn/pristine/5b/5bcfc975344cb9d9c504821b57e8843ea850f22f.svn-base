<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:form beanclass="etanah.view.strata.skim_strata_N9">

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Skim</legend>
            <p>
                <label>Nama Projek :</label>              
                <c:if test="${actionBean.mohonSkim.namaProjek ne null}">
                    ${actionBean.mohonSkim.namaProjek}
                </c:if>
            </p>

            <c:if test="${actionBean.bilblok ne 0 }">
                <p>
                    <label>Bilangan Blok :</label>                             
                    <c:if test="${actionBean.bilblok ne null}">
                        ${actionBean.bilblok}
                    </c:if>
                </p>
            </c:if>


            <c:if test="${actionBean.bilPetakKekal ne 0}">
                <p>
                    <label>Bilangan Petak :</label>             
                    ${actionBean.bilPetakKekal}                 
                </p>
            </c:if>

            <c:if test="${actionBean.bilPetakTanah ne 0 }">
                <p>
                    <label>Bilangan Petak Tanah :</label>                   
                    ${actionBean.bilPetakTanah}                   
                </p>
            </c:if>

            <c:if test="${actionBean.hidBlokSem}">          
                <p>
                    <label>Bilangan Blok Sementara :</label>                
                    <c:if test="${actionBean.bilbloksementara ne null}">
                        ${actionBean.bilbloksementara}
                    </c:if>
                </p>
                <p>
                    <label>Bilangan Petak Blok Sementara :</label>
                    <c:if test="${actionBean.bilPetakProv != 0}">
                        ${actionBean.bilPetakProv}
                    </c:if>
                </p> 
            </c:if> 


            <p>
                <label>Tarikh Daftar Strata :</label>
                <c:if test="${actionBean.tarikhDaftarStrata ne null}">
                    <fmt:formatDate value="${actionBean.tarikhDaftarStrata}" pattern="dd/MM/yyyy" />
                </c:if>
            </p>
            <p>
                <label>No. Buku Daftar Strata :</label>
                <c:if test="${actionBean.noBuku ne null}">
                    ${actionBean.noBuku}
                </c:if>
            </p>           
            <br>
        </fieldset>
    </div>
</s:form>