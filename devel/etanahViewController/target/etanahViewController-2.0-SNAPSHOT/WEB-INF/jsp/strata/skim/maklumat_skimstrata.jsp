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
<s:form beanclass="etanah.view.strata.skim_strata">

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.mohonSkim ne null}">
            <legend>Maklumat Skim</legend>
            <p>
                <label>Nama Projek :</label>              
                <c:if test="${actionBean.mohonSkim.namaProjek eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.mohonSkim.namaProjek ne null}">
                    ${actionBean.mohonSkim.namaProjek}
                </c:if>
            </p>

             <c:if test="${actionBean.negeri9}">
            <p>
                <label>Bilangan Blok :</label>              
                <c:if test="${actionBean.bilblok eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.bilblok ne null}">
                    ${actionBean.bilblok}
                </c:if>
            </p>
            <p>
                <label>Bilangan Petak :</label>
                <c:if test="${actionBean.bilPetakKekal == 0}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.bilPetakKekal != 0}">
                    ${actionBean.bilPetakKekal}
                </c:if>
            </p>
            <c:if test="${actionBean.hidBlokSem}">          
            <p>
                <label>Bilangan Blok Sementara :</label>                
                <c:if test="${actionBean.bilbloksementara eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.bilbloksementara ne null}">
                    ${actionBean.bilbloksementara}
                </c:if>
            </p>
            <p>
                <label>Bilangan Petak Blok Sementara :</label>
                <c:if test="${actionBean.bilPetakProv == 0}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.bilPetakProv != 0}">
                    ${actionBean.bilPetakProv}
                </c:if>
            </p> 
            </c:if> 
            </c:if>
            
           <c:if test="${!actionBean.negeri9}">
            <p>
                <label>Bilangan Blok :</label>              
                <c:if test="${actionBean.bilblok eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.bilblok ne null}">
                    ${actionBean.bilblok}
                </c:if>
            </p>
            <p>
                <label>Bilangan Petak :</label>
                <c:if test="${actionBean.mohonSkim.bilPetak eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.mohonSkim.bilPetak ne null}">
                    ${actionBean.mohonSkim.bilPetak}
                </c:if>
            </p>
                       
            <p>
                <label>Bilangan Blok Sementara :</label>                
                <c:if test="${actionBean.bilbloksementara eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.bilbloksementara ne null}">
                    ${actionBean.bilbloksementara}
                </c:if>
            </p>
            <p>
                <label>Bilangan Petak Blok Sementara :</label>
                <c:if test="${actionBean.mohonSkim.bilPetak eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.mohonSkim.bilPetak ne null}">
                    ${actionBean.mohonSkim.bilPetak}
                </c:if>
            </p> 
            </c:if>
            
            <p>
                <label>Tarikh Daftar Strata :</label>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.tarikhDaftar eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.tarikhDaftar ne null}">
                    <fmt:formatDate value="${actionBean.mohonHakmilik.hakmilik.tarikhDaftar}" pattern="dd/MM/yyyy" />
                </c:if>
            </p>
            <p>
                <label>No. Buku Daftar Strata :</label>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.noBukuDaftarStrata eq null}">
                    Tiada
                </c:if>
                <c:if test="${actionBean.mohonHakmilik.hakmilik.noBukuDaftarStrata ne null}">
                    ${actionBean.mohonHakmilik.hakmilik.noBukuDaftarStrata}
                </c:if>
            </p>           
            <br>
            </c:if>
            
            <c:if test="${actionBean.mohonSkim eq null}">
                <legend>Maklumat Skim</legend>
                    <p>
                        <label>Nama Projek :</label>              
                            ${actionBean.badananPengurusan.pihak.nama}
                    </p>
                    <p>
                        <label>Bilangan Blok :</label>              
                        <c:if test="${actionBean.bilblok eq null}">
                            Tiada
                        </c:if>
                        <c:if test="${actionBean.bilblok ne null}">
                            ${actionBean.bilblok}
                        </c:if>
                    </p>
                    <p>
                        <label>Bilangan Petak :</label>
                        <c:if test="${actionBean.bilPetakKekal eq null}">
                            Tiada
                        </c:if>
                        <c:if test="${actionBean.bilPetakKekal ne null}">
                            ${actionBean.bilPetakKekal}
                        </c:if>
                    </p>

                    <p>
                        <label>Bilangan Blok Sementara :</label>                
                        <c:if test="${actionBean.bilbloksementara eq null}">
                            Tiada
                        </c:if>
                        <c:if test="${actionBean.bilbloksementara ne null}">
                            ${actionBean.bilbloksementara}
                        </c:if>
                    </p>
                    <p>
                        <label>Bilangan Petak Blok Sementara :</label>
                        <c:if test="${actionBean.bilPetakProv eq null}">
                            Tiada
                        </c:if>
                        <c:if test="${actionBean.bilPetakProv ne null}">
                            ${actionBean.bilPetakProv}
                        </c:if>
                    </p> 
                    <p>
                        <label>Tarikh Daftar Strata :</label>
                        <c:if test="${actionBean.tarikhDaftar eq null}">
                            Tiada
                        </c:if>
                        <c:if test="${actionBean.tarikhDaftar ne null}">
                            <fmt:formatDate value="${actionBean.tarikhDaftar}" pattern="dd/MM/yyyy" />
                        </c:if>
                    </p>
                    <p>
                        <label>No. Buku Daftar Strata :</label>
                        <c:if test="${actionBean.noBukuDaftarStrata eq null}">
                            Tiada
                        </c:if>
                        <c:if test="${actionBean.noBukuDaftarStrata ne null}">
                            ${actionBean.noBukuDaftarStrata}
                        </c:if>
                    </p>
            </c:if>
        </fieldset>
    </div>
</s:form>