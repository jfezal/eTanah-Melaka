<%-- 
    Document   : pertanyaan_kertas_hakmilik
    Created on : Nov 1, 2012, 3:48:40 AM
    Author     : Zulhazmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:errors/>
<s:messages/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
    </head>
        
    <body>
<s:messages />
<s:errors />
<s:form beanclass="etanah.view.daftar.utiliti.PertanyaanKertasHakmilik" name="form1">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

    <fieldset class="aras1">
        <legend>Kertas Hakmilik </legend>
            <p><label>Nama Pendaftar :</label>
           <%--Senarai Pengguna Melaka--%>
            
            <c:if test="${actionBean.kodNegeri eq '04'}">
                
                <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                         <c:forEach items="${listUtil.senaraiPendaftar}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                
                <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>

                <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>

                <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                
            </c:if>
            <%--End Senarai Pengguna Melaka--%>
            
            
            <%--Negeri 9--%>
           
            <c:if test="${actionBean.kodNegeri eq '05'}">
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftar}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                    
                <c:if test="${actionBean.peng.kodCawangan.kod eq '05'}">
                    <s:select name ="idPengguna">
                        <s:option value = "">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiPenggunaPendaftar05}" var="item">
                            <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </c:if>  
            </c:if>
            <%--End Senarai Pengguna Negeri 9--%>
        </p>
        <p><label>&nbsp;</label> 
            <em>Atau</em>
        </p>
        <p>
            <label>No. Siri Kertas Hakmilik</label>
            <s:text name="noSiri"/>
        </p>
           
        <p><label>&nbsp;</label>
            <s:submit name="checkIdHakmilikKertas" value="Cari" class="btn"/>
        </p>
    </fieldset>



<div class="subtitle">
    <br>
   <br>
    <fieldset class="aras1">
        <br>
        <%--<c:if test="${fn:length(actionBean.senaraiHakmilikKertas)>0 }">--%>
            <c:if test="${actionBean.senaraiHakmilikKertas != null }">
        <legend>Hasil Carian Kertas Hakmilik Mengikut Pendaftar</legend>
        <div class="content" align="center" id="listpihak">
            <display:table class="tablecloth" style="width:90%;" name="${actionBean.senaraiHakmilikKertas}"
                            excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                <display:column title="No. Siri Awal"><div align="center">${line.noAwal}</div></display:column> 
                <display:column title="No. Siri Akhir"><div align="center">${line.noAkhir}</div></display:column> 
                <display:column title="Bilangan Kertas"><div align="center">${line.bilangan}</div></display:column> 
                <display:column title="Tarikh Ambil">                        
                    <div align="center">
                        <fmt:formatDate type="date"pattern="dd/MM/yyyy" value="${line.tarikhDiambil}"/>
                    </div>
                </display:column>
               
            </display:table>
        </div>
        </c:if>

   <br>
   
        <c:if test="${actionBean.hakmilikKertas != null }">
        <legend>Hasil Carian Kertas Hakmilik Mengikut No. Siri</legend>
        <div class="content" align="center" id="pihak">
               <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikKertas}"
                            excludedParams="selectedTab" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                <display:column title="No. Siri Awal"><div align="center">${line.noAwal}</div></display:column> 
                <display:column title="No. Siri Akhir"><div align="center">${line.noAkhir}</div></display:column> 
                <display:column title="Bilangan Kertas"><div align="center">${line.bilangan}</div></display:column>
                 <display:column title="Pendaftar"><div align="center">${line.pengguna.nama}</div></display:column>
                <display:column title="Tarikh Ambil">                        
                    <div align="center">
                        <fmt:formatDate type="date"pattern="dd/MM/yyyy" value="${line.tarikhDiambil}"/>
                    </div>
                </display:column>
               
            </display:table>
        </div>
        </c:if>
    </fieldset>
    </div>
</s:form>
</body>
</html>