<%-- 
    Document   : kertas_hakmilik_form
    Created on : Nov 15, 2012, 8:01:13 AM
    Author     : Zulhazmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
    </head>
        
    <body>
 <s:messages />
 <s:errors /> 
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.daftar.utiliti.KemaskiniKertasHakmilik">
    
    
    <div>
        <%--<s:hidden name="${actionBean.hakmilikKertas.idHakmilikKertas}"/>--%>
        <s:hidden name="hakmilikKertas.idHakmilikKertas"/>
        <fieldset class="aras1">
            <legend>
                Kemaskini Kertas Hakmilik
            </legend>
                
            <p> 
                <label>No. Siri Dari :</label>
                <s:text name="hakmilikKertas.noAwal"  style="width:10%"/><font size ="2" color ="black" > Hingga</font>
                <s:text name="hakmilikKertas.noAkhir" style="width:10%"/><font size ="2" color="red"> [Contoh: PDTMT7118900]</font>
                
            </p><br>
            
            <%--Jenis Kertas Melaka--%>
            <%--<c:if test="${actionBean.peng.negeri.kod eq '04'}">--%>
            <%--<c:if test="${actionBean.kodNegeri eq '04'}">
                <p><label>Jenis Kertas :</label>
                    <s:radio  name="hakmilikKertas.jenisKertas" value="DHDE" ></s:radio> DHDE
                    <s:radio  name="hakmilikKertas.jenisKertas" value="DHKE" ></s:radio> DHKE
                    </p>
            </c:if>--%>
            <%--End Jenis Kertas Melaka--%>
              
            <p><label>Tarikh Ambil :</label>
                <%--<s:text name="hakmilikKertas.tarikhDiambil"  class="datepicker"
                        onblur="editDateBlur(this, 'DD/MM/YYYY')"
                        onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                        formatPattern="DD/MM/YYYY"
                        onkeyup="return editDate(this, 'DD/MM/YYYY', event);" id="tarikhAmbil"/> --%>
                <s:text name="hakmilikKertas.tarikhDiambil"  class="datepicker" formatPattern="dd/MM/yyyy" id="tarikhAmbil"/>
                <font size="1" color="red">[hh/bb/tttt]</font>
            </p>
            
            <p><label>Nama Pengguna :</label>

                <%--Senarai Pengguna Melaka--%>
                <%--<c:if test="${actionBean.peng.negeri.kod eq '04'}">--%>
                <c:if test="${actionBean.kodNegeri eq '04'}">

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPendaftar}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>
                    
                </c:if>
                <%--End Senarai Pengguna Melaka--%>
                
                
                <%--Negeri 9--%>
                <%--<c:if test="${actionBean.peng.negeri.kod eq '05'}">--%>
                <c:if test="${actionBean.kodNegeri eq '05'}">
                
                    <c:if test="${actionBean.peng.kodCawangan.kod eq '00'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPendaftar}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '01'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarMT}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '02'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarJasin}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '03'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftarAG}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>

                    <c:if test="${actionBean.peng.kodCawangan.kod eq '05'}">
                        <s:select name ="hakmilikKertas.pengguna.idPengguna" id ="idPengguna">
                            <s:option value = "">Sila Pilih</s:option>
                            <c:forEach items="${listUtil.senaraiPenggunaPendaftar05}" var="item">
                                <s:option value = "${item.idPengguna}">${item.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </c:if>  
                </c:if>
                <%--End Senarai Pengguna Negeri 9--%>
            </p>           
            
            <br>            
            <p>
                <label>&nbsp;</label><s:submit name="update" value="Simpan" class="btn"/>
                <s:submit name="kembali" value="Batal" class="btn"/>
            </p>
        </fieldset>
    </div>
</s:form>

    </body>
</html>