<%-- 
    Document   : status_tempoh_notis
    Created on : Mar 18, 2014, 2:28:11 PM
    Author     : Fauzan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>


<script type="text/javascript">
 
  
    $( document ).ready(function() {
             
                          
             var bil =  ${fn:length(actionBean.listNotis)};
             //alert(bil);
            
             for (var i = 0; i < bil; i++){
                  
                  var tarikhTerima = document.getElementById("tarikhTerima"+i).value; 
                  var tarikhTampal = document.getElementById("tarikhTampal"+i).value;
                  var tempohHari = parseInt(document.getElementById("tempohHari"+i).value);
                  var tempohBulan = parseInt(document.getElementById("tempohBulan"+i).value);
              
                 if (tarikhTerima != 0 && tarikhTampal == 0){
                     //alert(tarikhTampal);
                                         
                     var hari = tempohBulan * 30;
                     tempohHari += hari;
                    
                     //$("#tempohHari"+i).val(tempohHari);
                    
                     var vsplit01 = tarikhTerima.split('/');
                     var fulldate01 = vsplit01[1]+'/'+vsplit01[0]+'/'+vsplit01[2];
                 
                     var sdate01 = new Date(fulldate01);
                     sdate01.setDate(sdate01.getDate() + tempohHari); 
                  
                     var dd01 = sdate01.getDate();
                     var mm01 = sdate01.getMonth() + 1;
                     var y01 = sdate01.getFullYear();
 
                     var finalDate = dd01 + '/'+ mm01 + '/'+ y01;
                  //alert("final date "+finalDate);
                     $("#finalDate"+i).val(finalDate);
                 } 
                 
                
               else  if (tarikhTampal != 0 && tarikhTerima == 0) {
                  
                  //alert(tarikhTampal);
                  var vsplit02 = tarikhTampal.split('/');  
                  var fulldate02 = vsplit02[1]+'/'+vsplit02[0]+'/'+vsplit02[2];
                  
                     var sdate02 = new Date(fulldate02);
                     sdate02.setDate(sdate02.getDate() + tempohHari); 
                  
                     var dd02 = sdate02.getDate();
                     var mm02 = sdate02.getMonth() + 1;
                     var y02 = sdate02.getFullYear();
 
                     var finalDate = dd02 + '/'+ mm02 + '/'+ y02;
                  //alert("final date "+finalDate);
                    $("#finalDate"+i).val(finalDate);
                 }
                 
                 else{
                     
                     var vsplit01 = tarikhTerima.split('/');
                     var fulldate01 = vsplit01[1]+'/'+vsplit01[0]+'/'+vsplit01[2];
                 
                     var sdate01 = new Date(fulldate01);
                     sdate01.setDate(sdate01.getDate() + tempohHari); 
                  
                     var dd01 = sdate01.getDate();
                     var mm01 = sdate01.getMonth() + 1;
                     var y01 = sdate01.getFullYear();
 
                     var finalDate = dd01 + '/'+ mm01 + '/'+ y01;
                  //alert("final date "+finalDate);
                     $("#finalDate"+i).val(finalDate);
                 }
                     
                   
                   }
                     
      }
        
        
        ); 

  function popup(m , h){
        window.location='${pageContext.request.contextPath}/enf/utiliti_status_tempoh_notis?popupNotis&idPermohonan='+ m + '&idHakmilik='+ h;
        
    }
      
</script>


             
<s:form beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiStatusTempohNotisActionBean" name="form2">
        <s:errors/>
        <s:messages/>
        
        
     <div class="subtitle">
       <div id="notisTempohDiv">
        <fieldset class="aras1">
            <legend>Status Tempoh Notis</legend>
             <font size="1" color="Red">Sila masukkan ID Hakmilik atau ID Permohonan untuk carian notis</font>
            
             <p>
                <label><font color="red">*</font>ID Hakmilik :</label>
                <input type="text" name="idHakmilik" onkeyup="this.value=this.value.toUpperCase();"/>
                    <s:submit name="searchMilik" value="Cari" class="btn"/>
            </p>
            
            <label> atau </label>
            <br>
            
            <p>
                <label><font color="red">*</font>ID Permohonan :</label>
                 <input type="text" name="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                 <s:submit name="checkID" value="Cari" class="btn"/>
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
                              requestURI="/enf/utiliti_status_tempoh_notis" id="line">
                 
                   <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                   <display:column title="Nama Notis" class="rowCount">${line.kodNotis.nama}</display:column>
                    <display:column title="Status Serahan" property="kodStatusTerima.nama" class="${line_rowNum}"/>
                    <display:column title="Tarikh Terima" class="${line_rowNum}">            
                          <fmt:formatDate pattern="d/M/yyyy" value="${line.tarikhTerima}"/>
                            <s:hidden name="listNotis[${line_rowNum-1}].tarikhTerima" id="tarikhTerima${line_rowNum-1}"
                             value="${line.tarikhTerima}" formatType="Date" formatPattern="dd/MM/yyyy"/>
                    </display:column>
                     <display:column title="Tarikh Tampal" class="${line_rowNum}">
                          <fmt:formatDate pattern="d/M/yyyy" value="${line.tarikhTampal}"/> 
                           <s:hidden name="listNotis[${line_rowNum-1}].tarikhTampal" id="tarikhTampal${line_rowNum-1}"
                             value="${line.tarikhTampal}" formatType="Date" formatPattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Tempoh Notis" class="${line_rowNum}">
                        <c:if test="${line.tempohHari eq 0}"> Tiada </c:if>  
                        <c:if test="${line.tempohHari != 0 && line.tempohBulan != 0}"> ${line.tempohBulan} Bulan ${line.tempohHari} Hari </c:if>
                        <c:if test="${line.tempohHari != 0 && line.tempohBulan == 0}"> ${line.tempohHari} Hari </c:if> 
                          <s:hidden name="listNotis[${line_rowNum-1}].tempohHari" id="tempohHari${line_rowNum-1}"
                             value="${line.tempohHari}"/>
                           <s:hidden name="listNotis[${line_rowNum-1}].tempohBulan" id="tempohBulan${line_rowNum-1}"
                             value="${line.tempohBulan}"/>
                    </display:column>
                  
                    <display:column title="Tarikh Luput Notis" >
                         <c:if test="${line.tempohHari eq 0}"> Tiada </c:if>
                         <c:if test="${line.tempohHari != 0}"> <s:text name="" id="finalDate${line_rowNum-1}" readonly="readonly"/> </c:if>
                          
                          <%-- <s:hidden name="listNotis[${line_rowNum-1}].fdate" id="finalDate${line_rowNum-1}"
                             value="${finalDate}"/>--%>
                    </display:column> 
                  
                    
                    
                </display:table>
             </div>    
         </div> 
             
    </div>  
</s:form>