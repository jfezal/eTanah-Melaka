<%-- 
    Document   : pemohonan_sdp
    Created on : 25 Julai 2011, 12:34:06 PM
    Author     : Akmal
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript">
function selectCheckBox()
            {
            var e= $('#sizeKod').val();
//            alert(e) ;
            var cnt=0;
            var data = new Array() ;
            if(e==1){
                for(cnt=0;cnt<e;cnt++)
                 {
//                     alert(document.frm.checkmate.checked);
                     if(document.frm.checkmate.checked) {
//                         alert(document.frm.checkmate.value) ;
                     data[cnt] = document.frm.checkmate.value ;
                     }
                     else{
                         data[cnt] = "T" ;
                     }
                  }
            }else if(e>1){
                for(cnt=0;cnt<e;cnt++)
                 {
//                     alert(document.frm.checkmate[cnt].checked);
                     if(document.frm.checkmate[cnt].checked) {
//                         alert(document.frm.checkmate[cnt].value) ;
                     data[cnt] = document.frm.checkmate[cnt].value ;
                     }
                     else{
                         data[cnt] = "T" ;
                     }
                  }
            }
            
//                    alert(data) ;
                if(confirm("Adakah anda pasti?")) {
                var url = '${pageContext.request.contextPath}/pelupusan/kemasukanSDP?tukarStatus&item='+data ;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    alert("Rekod telah berjaya di aktifkan") ;
                    location.reload(true);
                },'html');
                }
            }
            function refreshSDP(){
                alert("asdasd");
                var url = '${pageContext.request.contextPath}/pelupusan/kemasukanSDP?refreshPage';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.kemasukanSDPActionBean" name="frm"> 
 <s:messages/>
 <s:errors/>
  <table width="90%" border="0" align="center">
      <tr>
          <td>
              <div class="subtitle" style="text-transform: capitalize">
                  <fieldset class="aras1">
                      <legend>Penukaran SDP </legend>
                      <%--<display:table  name="${actionBean.senaraiSDP}" id="line9" class="tablecloth">
                        <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idPermohonan}"/>
                        <display:column title="No">${line9_rowNum}</display:column>
                        <display:column title="ID Permohonan"  property="idPermohonan"/>
                        <display:column title="Jenis Urusan" property="kodUrusan.nama"/>
                        <display:column title="Tindakan">
                        <div align="center">
                            <img alt='Klik Untuk aktifkan' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line9_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeLaporKawasan('${line9.idPermohonan}')"/>
                        </div>
                        </display:column>
                    </display:table>--%>
                      
                      <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
                    <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiSDP}" pagesize="100" cellpadding="0" cellspacing="0" requestURI="/pelupusan/kemasukanSDP" id="line">
                            <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.idPermohonan}"/></display:column>
                            <display:column title="No">${line_rowNum}</display:column>
                            <display:column title="ID Permohonan"  property="idPermohonan"/>
                            <display:column title="Jenis Urusan" property="kodUrusan.nama"/>
                      </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.senaraiSDP) > 0}" >
                            <p><label>&nbsp;</label>
                                   <s:button name="Aktifkan" value="Aktifkan" class="btn" onclick="javascript:selectCheckBox();"/>
                            </p>
                    </c:if>
                  </fieldset>
              </div>
          </td>
      </tr>
  </table>
<!--    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Permohonan SDP</legend></br> 
            <p align="center">Kemasukan Permohonan ke dalam SDP</p>
            </br>
            <p align="center">
                <s:button name="masukSDP" value="Masuk SDP" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/></p>
            </br>
        </fieldset>
    </div>-->
 </s:form>
            
 
  
