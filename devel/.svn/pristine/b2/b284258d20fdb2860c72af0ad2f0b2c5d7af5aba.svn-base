<%-- 
    Document   : papar_pihak_waris
    Created on : Jan 1, 2015, 12:29:40 AM
    Author     : zairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
        function popupTambah(id) {
             var url = "${pageContext.request.contextPath}/daftar/pembetulan_pihak?tambahWaris&idHakmilikPihakBerkepentingan=" + id;
             window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
            //maximizeWindow();
        }
        function popupEditPihakWaris(val,id) {
             var url = "${pageContext.request.contextPath}/daftar/pembetulan_pihak?EditPihakWaris&id_hpw=" + val+ "&idHakmilikPihakBerkepentingan=" + id;
             window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
            //maximizeWindow();
        }
        function removeWaris(val,id) {
              var answer = confirm("adakah anda pasti untuk Hapus?");           
              
              var frm = document.form1;
              if(answer) {
                var url = '${pageContext.request.contextPath}/daftar/pembetulan_pihak?deletePihakWaris&id_hpw=' + val+ '&idHakmilikPihakBerkepentingan=' + id;
                frm.action = url;
                frm.submit();
              }         
          }
</script>
<s:form beanclass="etanah.view.stripes.nota.pembetulanPihakActionBean" name="form1">
 <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test = "${actionBean.hakmilikPihak.jenis.kod eq 'PA'}" >Pemegang Amanah Bagi Pihak</c:if>                
                <c:if test = "${actionBean.hakmilikPihak.jenis.kod eq 'PP'}" >Pentadbir Bagi Pihak</c:if>  
            </legend>
            <br>
            <div align="center">                
                    <display:table class="tablecloth" name="${actionBean.hwList}"
                                   cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil"><div align="right">${line_rowNum}</div></display:column>
                        <display:column title="Nama">
                            ${line.nama}
                        </display:column>
                        <display:column title="No Pengenalan">
                            ${line.noPengenalan}
                        </display:column>
                        <display:column title="Syer" style="width:5%;">
                            <div align="center">
                                <c:if test = "${line.syerPembilang ne null}" >
                                    ${line.syerPembilang}/${line.syerPenyebut}
                                </c:if>
                            </div>
                        </display:column>
                        <display:column title="Kemaskini">
                                <div align="center">
                                    <a href="#" onclick="popupEditPihakWaris('${line.idWaris}','${actionBean.hakmilikPihak.idHakmilikPihakBerkepentingan}')">Kemaskini</a>
                                </div>
                        </display:column>
                        <display:column title="Hapus">             
                            <div align="center">
                                <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="removeWaris('${line.idWaris}','${actionBean.hakmilikPihak.idHakmilikPihakBerkepentingan}')" style="cursor:hand">
                            </div>
                        </display:column>  
                    </display:table>                
            </div>
            <br/>
        </fieldset>
        <br>
        <div  align="center">
            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
            <s:button name="tambahWaris"  id="tambahWaris" value="Tambah" class="btn" onclick="popupTambah('${actionBean.hakmilikPihak.idHakmilikPihakBerkepentingan}');" />                 
        </div>
    </div>
</s:form>
