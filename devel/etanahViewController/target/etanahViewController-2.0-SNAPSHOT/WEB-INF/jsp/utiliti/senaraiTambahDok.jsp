<%--
    Document   : senaraiTambahDok
    Created on : Nov 5, 2011, 10:07:38 AM
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
         function addDokumen(frm,val,val1,val2){
                var len = $('.dok').length;
           
                var param = '';
                for(var i=0; i<=len; i++){
                    var ckd = $('#chkbox_pemohon_'+i).is(":checked");
                    if(ckd){
                        param = param + '&idDokumen=' + $('#chkbox_pemohon_'+i).val();
                    }
                }
                if(param == ''){
                    alert('Tiada Dokumen.');
                    
                }
                
             
                var url = '${pageContext.request.contextPath}/utiliti/senarai_mohon_ruj_luar_dok?simpanDokumen'+param +'&idRujukan='+val+'&jabatan='+val1+'&catatan='+val2;
                frm.action = url;
                frm.submit();

              
            }
            
            function goTo(frm, value1,value2,value3,value4) {
            var url = '${pageContext.request.contextPath}/utiliti/senarai_mohon_ruj_luar_dok?findMohonRujLuarDok&idMohon='+value1+'&idRujukan='+value2+'&jabatan='+value3+'&catatan='+value4;
            frm.action = url;
            frm.submit();
        }
            
    
        
    </script>

    <s:form beanclass="etanah.view.utility.CarianDokumenActionBean" name ="senaraiTambahRujLuarDok" id ="senaraiTambahRujLuarDok">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag" >
            <fieldset class ="aras1" >
         
                <p><label>Keterangan Dokumen :</label><s:text name="keyword" value="${actionBean.keyword}"></s:text></p>
                <p><s:hidden name ="idMohon" value="${actionBean.idMohon}"></s:hidden></p>
                <p><s:hidden name ="keyword" value="${actionBean.keyword}"></s:hidden></p>
                <p><s:hidden name = "idruj" value="${actionBean.idruj}"></s:hidden></p>
                <p><s:hidden name = "catatan" value="${actionBean.catat}"></s:hidden></p>
                <p><s:hidden name = "jabatan" value="${actionBean.jabatan}"></s:hidden></p>
                <center>
                <p><s:submit name="cariFilterTambahDok"value="Cari" class="btn" ></s:submit>&nbsp;&nbsp;<s:button class="btn" value="Kembali" name="Kembali" onclick="goTo(document.forms.senaraiTambahRujLuarDok,'${actionBean.idMohon}','${actionBean.idruj}','${actionBean.jabatan}','${actionBean.catat}')"></s:button></p>
               </center>
            </fieldset>
        </div>    
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Dokumen
                    </legend>
                    <display:table  name="${actionBean.listFilterDokumen}" id="row"  class="tablecloth" pagesize="20" requestURI="${pageContext.request.contextPath}utility/senarai_mohon_ruj_luar_dok">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${row_rowNum}</display:column>
                        <display:column  style  = "verticle-align:baseline"><s:checkbox name="checkbox" id="chkbox_pemohon_${row_rowNum}" value="${row.idDokumen}"/></display:column>
                        <display:column title="Kod Dokumen" class="dok">
                                ${row.kodDokumen.kod}
                            </display:column>
                        <display:column property ="kodDokumen.nama" sortable="true" title="Keterangan Dokumen" style = "verticle-align:baseline"></display:column>
                    </display:table>
                   <s:submit class="btn" value="Pilih" name="pilih" id="pilih" onclick="addDokumen(document.forms.senaraiTambahRujLuarDok,'${actionBean.idruj}','${actionBean.jabatan}','${actionBean.catat}')" onmouseover="this.style.cursor='pointer';"></s:submit>
                </fieldset>
            </div>
    </s:form>    
</div>


