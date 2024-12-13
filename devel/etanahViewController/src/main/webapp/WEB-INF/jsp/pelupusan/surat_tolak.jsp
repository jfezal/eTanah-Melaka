<%--
    Document   : surat_tolak
    Created on : May 20, 2011, 5:49:25 PM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>

<s:form beanclass="etanah.view.stripes.pelupusan.SuratTolakActionBean">

 <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Sebab Tolak</legend>
            <div align="left">
                 <%--<c:if test="${actionBean.ditundatangan eq null}">--%>
                 <%--<p><label>Ditandatangan Oleh :</label></p>
                 <s:select name="ditundatangan" id="namapguna">
                           <s:option label="Sila Pilih" value="" />                          
                           <c:forEach items="${actionBean.penggunaList}" var="i" >                              
                               <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                           </c:forEach>
                       </s:select>
                </div><br/>
                <div id="buttontandatangan" align="center">
                    <s:button name="simpanTandatangan" class="longbtn" value="Simpan Tandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                 </div>
                 <%--</c:if>--%>
                 <br>
            <p>
                <label>Sebab Tolak :</label>
                <s:textarea name="permohonan.catatan" class="normal_text" rows="10" cols="70"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset >
    </div>

</s:form>
