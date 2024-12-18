<%-- 
    Document   : senaraiRujLuarDok
    Created on : Nov 1, 2011, 12:41:12 PM
    Author     : Aziz
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<div class= "all">
    <script type="text/javascript">
     
        //     $(document).ready(function(){
        //        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        //    });

        
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        
        }
    
        function goTo(frm, value1,value2,value3,value4) {
            var url = '${pageContext.request.contextPath}/utiliti/senarai_mohon_ruj_luar_dok?deleteMohonRujLuarDok&idDokumen='+value1+'&idRujukan='+value2+'&jabatan='+value3+'&catatan='+value4;
            if(confirm("Adakah anda pasti untuk hapus dokumen?")){
                frm.action = url;
            frm.submit();
            }
        }
        
         function goTo2(frm,value1,value2) {
            var url = '${pageContext.request.contextPath}/utiliti/senarai_mohon_ruj_luar_dok?tambahMohonRujLuarDok&jabatan='+value1+'&catatan='+value2;
            frm.action = url;
            frm.submit();
        }
        
         function goTo3(frm,value1) {
            var url = '${pageContext.request.contextPath}/utiliti/utiliti_dok?findPermohonan&IdPermohonan='+value1;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>

    <s:form beanclass="etanah.view.utility.CarianDokumenActionBean" name ="senaraiRujLuarDok" id ="senaraiRujLuarDok">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
         
                <p><label>Nama Jabatan :</label>${actionBean.jabatan}</p>
                <!--<p><label>Catatan :</label>${actionBean.catat}</p>-->
                <p><s:hidden name = "idruj" value="${actionBean.idruj}"></s:hidden></p>
                  <p><s:hidden name ="idMohon" value="${actionBean.idMohon}"></s:hidden></p>
            </fieldset>
        </div>    
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Dokumen yang Disertakan
                    </legend>
                    <display:table  name="${actionBean.rujLuarDok}" id="line"  class="tablecloth" pagesize="" requestURI="${pageContext.request.contextPath}utility/senarai_mohon_ruj_luar_dok">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>
                        <display:column property ="dokumen.kodDokumen.kod" sortable="true" title="Kod Dokumen" style = "verticle-align:baseline" ></display:column>
                        <display:column property ="dokumen.kodDokumen.nama" sortable="true" title="Keterangan Dokumen" style = "verticle-align:baseline"></display:column>
                        
                   <display:column title="Hapus">
                            <div align ="center">
                                <img alt='Klik untuk hapus dokumen' border='0' src='${pageContext.request.contextPath}/images/delete_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="goTo(document.forms.senaraiRujLuarDok, '${line.dokumen.idDokumen}','${line.permohonanRujukanLuar.idRujukan}','${actionBean.jabatan}','${actionBean.catat}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                <s:button class="btn" value="Tambah" name="tambah" onclick="goTo2(document.forms.senaraiRujLuarDok,'${actionBean.jabatan}','${actionBean.catat}')" ></s:button>&nbsp;&nbsp;<s:button class="btn" value="Kembali" name="Kembali" onclick="goTo3(document.forms.senaraiRujLuarDok,'${actionBean.idMohon}')"></s:button>
                </fieldset>
            </div>
    </s:form>
</div>


