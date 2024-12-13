<%-- 
    Document   : senaraiPermitByDaerah
    Created on : Nov 5, 2015, 9:40:27 AM
    Author     : Hazwan
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
        maximizeWindow();    
        
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
      
        function papar(frm,noF,noP,jB) {         
            var url = '${pageContext.request.contextPath}/digiSignPermit?view&noFailRuj='+noF+'&noPermit='+noP+'&borang='+jB;
            frm.action = url;
            frm.submit();
        }
        
        function batal(frm,noF,noP) {
            if (confirm('Adakah Pasti Untuk Hapuskan Maklumat Permit Ini ?')){
                var url = '${pageContext.request.contextPath}/digiSignPermit?batal&noFailRuj='+noF+'&noPermit='+noP;
                frm.action = url;
                frm.submit();
            }else{
                return false;
            }
            
        }
        
        function zeroPad(num,count)
        {
            var numZeropad = num + '';
            while(numZeropad.length < count) {
                numZeropad = "0" + numZeropad;
            }
            return numZeropad;
        }
    
        function formatNoPermit(){
            var num = $('#noPermit').val();
            var noPermit = zeroPad(num,'8');
            $('#noPermit').val(noPermit);
        }
    
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.permit.DSPermitActionBean" name ="senaraiPermit" id ="senaraiPermit">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Senarai Permit Untuk Tandatangan Digital</legend>

                <p>
                    <label><font color="red"></font>No. Fail Rujukan :</label>
                        <s:text name="noFailRuj" id="noFailRuj" size="12" style="width:200px;" maxlength="30" onblur="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label><font color="red"></font>No. Permit/Lesen :</label>
                        <s:text name="noPermit" id="noPermit" size="12" style="width:200px;" maxlength="8" onkeyup="validateNumber(this,this.value);" onblur="formatNoPermit()" />
                </p>

                <p>
                    <label><font color="red"></font>Borang/Permit :</label>
                        <s:select name="borang" id="borang" >
                            <s:option value="0">Pilih</s:option>
                        <s:option value="A">Borang 4Ae (Lesen Pendudukan Sementara (Borang Am))</s:option>
                        <s:option value="B">Borang 4Be (Lesen Pendudukan Sementara (Borang Khas))</s:option>
                        <s:option value="C">Borang 4Ce (Permit Untuk Memindah Bahan Batuan)</s:option>
                        <s:option value="D">Borang 4De (Permit Bagi Penggunaan Ruang Udara)</s:option>
                    </s:select>
                </p>
                <br/>
                <p align="center"><s:submit name="cari" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('senaraiPermit')" /></p>
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Permit
                    </legend>
                    <display:table  name="${actionBean.listPermit}" id="line"  class="tablecloth" pagesize="10" requestURI="${pageContext.request.contextPath}/digiSignPermit">
                        <display:column title="Bil" sortable = "true" style  = "verticle-align:baseline">${line_rowNum}</display:column>                   
                        <display:column property ="permohonan.idPermohonan" sortable="true" title="No. Fail Rujukan" style = "verticle-align:baseline"></display:column>
                        <display:column property ="noPermit" sortable="true" title="No. Permit/Lesen" style = "verticle-align:baseline"></display:column>
                        <display:column property ="permohonan.catatan" sortable="true" title="Jenis Borang" style = "verticle-align:baseline"></display:column>                                  
                        <display:column title="Tindakan">
                            <div align ="center">                            
                                <img alt='Klik untuk paparan permit' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="papar(document.forms.senaraiPermit,'${line.permohonan.idPermohonan}','${line.noPermit}','${line.permohonan.catatan}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                        <display:column title="Batal">
                            <div align ="center">                            
                                <img alt='Klik untuk batalkan permit' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="batal(document.forms.senaraiPermit,'${line.permohonan.idPermohonan}','${line.noPermit}')" onmouseover="this.style.cursor='pointer';"></img>
                            </div>
                        </display:column>
                    </display:table>
                </fieldset>
            </div>
        </c:if>
    </s:form>
</div>





