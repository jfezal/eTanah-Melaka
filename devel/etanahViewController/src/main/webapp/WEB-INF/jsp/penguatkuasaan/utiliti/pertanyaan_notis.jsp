<%-- 
    Document   : pertanyaan_notis
    Created on : Mar 12, 2014, 12:12:44 PM
    Author     : Tengku.Fauzan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>



<script type="text/javascript">
    
   function popup(m , h){
        window.location='${pageContext.request.contextPath}/enf/utiliti_pertanyaan_notis?popupNotis&idPermohonan='+ m + '&idHakmilik='+ h;
        
    }

    
</script>

<s:form beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiPertanyaanNotisActionBean" name="form1">
        <s:errors/>
        <s:messages/>
        
        
     <div class="subtitle">
       <div id="notisPertanyaanDiv">
        <fieldset class="aras1">
            <legend>Carian Pertanyaan Notis</legend>
            <font size="1" color="Red">Sila masukkan ID Hakmilik atau ID Permohonan untuk carian notis</font>
            
             <p>
                <label><font color="red">*</font>ID Hakmilik :</label>
                <input type="text" name="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
                    <s:submit name="trackMilik" value="Cari" class="btn"/>
            </p>
            
            <label> atau </label>
            <br>
            <p>
                <label><font color="red">*</font>ID Permohonan :</label>
                <input type="text" name="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                    <s:submit name="trackID" value="Cari" class="btn"/>
            </p>
            
             <legend>Senarai Notis</legend>
             
               <p>
                        <label for="idHakmilik">ID Hakmilik:</label>
                        ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik} 
                     <c:if test="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik eq null}"> Tiada rekod 
                     </c:if>  
                 </p>
                 <br>             
                 <p>
                      <label for="idPermohonan">ID Permohonan:</label>
                        ${actionBean.permohonan.idPermohonan}
                       <c:if test="${actionBean.permohonan.idPermohonan eq null}"> Tiada rekod </c:if> 
                      <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                 </p>
                 <br>
                  <p>
                        <label for="Urusan">Urusan :</label>
                        ${actionBean.permohonan.kodUrusan.nama}
                         <c:if test="${actionBean.permohonan.kodUrusan.nama eq null}"> Tiada rekod </c:if> 
                  </p>
           
         </fieldset>
       </div>   
       
       <div class="content" align="center">
            <div id="ImejDiv">
               <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" 
                 requestURI="/enf/utiliti_pertanyaan_notis" id="line">
                   <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                   <display:column title="ID Permohonan"> <a href="#" onclick="popup('${line.permohonan.idPermohonan}','${line.hakmilik.idHakmilik}');return false;">${line.permohonan.idPermohonan}</a>
                   </display:column> 
                   <display:column title="Urusan"> ${line.permohonan.kodUrusan.nama}</display:column>
                                     
                </display:table>
             </div>    
        </div>                       
                    
                    
                    
                    
       <div class="content" align="center">
            <div id="ImejDiv">
               <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" 
                 requestURI="/enf/utiliti_pertanyaan_notis" id="line">
                   <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                   <display:column title="Nama Notis" >${line.kodNotis.nama}</display:column>
                    <display:column title="Tarikh Notis" class="${line_rowNum}">
                          <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhNotis}"/> 
                    </display:column>
                    <display:column title="Status Serahan" property="kodStatusTerima.nama" class="${line_rowNum}"/>
                    <display:column title="Tarikh Serah" class="${line_rowNum}">
                         Hantar :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhHantar}"/> <br>
                         Terima :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTerima}"/>
                    </display:column>
                    <display:column title="Tarikh Tampal" class="${line_rowNum}">
                          <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTampal}"/> 
                    </display:column>
                    <display:column title="Penghantar Notis"> ${line.penghantarNotis.nama} <br>
                    No.K/P:(${line.penghantarNotis.noPengenalan})
                    </display:column>      
                    <display:column title="Penerima Notis"> ${line.penerimaNotis}</display:column>
                    <display:column title="Tempat Tampal"> ${line.tempatTampal}</display:column>    
                </display:table>
             </div>    
         </div>  
                 
       
         
    </div>  
</s:form>