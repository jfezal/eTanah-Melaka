<%--
    Document   :  syorPHLA(Editable).jsp
    Created on :  July 03, 2012, 04:53:00 PM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYOR PENOLONG PEGAWAI TANAH</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
   
    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:choose>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                $('#phlasokong').show();
                $('#phlatidaksokong').hide();
        </c:when>
        <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                $('#phlatidaksokong').show();
                $('#phlasokong').hide();
        </c:when>
        <c:otherwise>
                $('#phlasokong').hide();
                $('#phlatidaksokong').hide();
        </c:otherwise>
    </c:choose>    
        <c:if test="${actionBean.ksn ne null}">
            $('#syorKpsn').val('${actionBean.ksn}'); 
    </c:if>  
         
        
        }); //END OF READY FUNCTION
    
        function refreshpage2(type){
            //        alert(type);
            var url = '${pageContext.request.contextPath}/pembangunan/laporanTanahv2?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function showphlasokong(val) {
            $('#phlasokong').show();
            $('#phlatidaksokong').hide();
            if(val == 'ST'){
                val = 'SL';
            }
            setKpsn2(val);
        }
    
        function showphlatidaksokong(val) {
            $('#phlatidaksokong').show();
            $('#phlasokong').hide();
            if(val == 'SL'){
                val = 'ST';
            }
            setKpsn2(val);
        }
    
        function addRow(index,f)
        {   
            NoPrompt();
            var kodksn= document.getElementById("ksn").value;
            var url = '${pageContext.request.contextPath}/pembangunan/laporanTanahv2?tambahRow&index='+index+'&kodksn='+kodksn;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function deleteRow(idKandungan,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pembangunan/laporanTanahv2?deleteRow&idKandungan='+idKandungan+'&tName='+tName+'&typeName=PPT',q,
                function(data){
                    $('#page_div').html(data);

                }, 'html');
                self.refreshpage2('syorPPT') ;
            }
        }
    
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            opener.refreshV2ManyHakmilik('main',idHakmilik);
            self.close();
        }
        
        function setKandungan(i,idLaporUlas){
            var index = i;
            var kandungan = $('#kandungan5'+index).val();
            $('#'+idLaporUlas+'kandunganUlas').val(kandungan);
        }
        function setKpsn(){
            NoPrompt();
            //alert();
            //            var kpsn = document.getElementById('ksn').value;
            //            if(kpsn != null)
            //                $('#syorKpsn').val(kpsn);
        }
        function setKpsn2(val){
            $('#ksn').val(val);  
            $('#syorKpsn').val(val);
        }
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
             
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            Syor Penolong Pegawai Tanah
                        </c:if>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                    Syor Penolong Pegawai Tanah
                                </c:when>
                                <c:otherwise>
                                    Syor Penolong Pegawai Tanah
                                </c:otherwise>
                            </c:choose>
                        </c:if>                    
                    </legend>
                </div>
                <c:choose>
                    <c:when test="${actionBean.kodNegeri eq '04'}">
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Syor : 
                                </td>
                                <td>
                                    <s:radio name="ksn" id="ksn" value="SL" onclick="showphlasokong(this.value);" />&nbsp;Sokong
                                    <s:radio name="ksn" id="ksn" value="ST" onclick="showphlatidaksokong(this.value);" />&nbsp;Tidak Sokong                                             
                                    <s:hidden name="syorKpsn" id="syorKpsn"/>
                                </td>

                            </tr>
                        </table>
                        <br/>
                        <div id="phlasokong">
                            
                        </div>
                        <div id="phlatidaksokong">
                            
                        </div>
                    </c:when>
                    <c:when test="${actionBean.kodNegeri eq '05'}">
                        <table class="tablecloth" align="center">
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Syor : 
                                </td>
                                <td>
                                    <s:radio name="ksn" id="ksn" value="SL" onclick="showphlasokong(this.value);" />&nbsp;Sokong
                                    <s:radio name="ksn" id="ksn" value="ST" onclick="showphlatidaksokong(this.value);" />&nbsp;Tidak Sokong                                             
                                    <s:hidden name="syorKpsn" id="syorKpsn"/>
                                </td>

                            </tr>
                        </table>
                        <br/>
                        <div id="phlasokong">
                            
                        </div>
                        <div id="phlatidaksokong">
                            
                        </div>
                    </c:when>
                </c:choose>
                <legend>Ulasan</legend>
                <table class="tablecloth" border="0" align="center">
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="${num})"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiLaporanKandungan1[${i-1}].ulasan" cols="80"  rows="5" onblur="setKandungan(${i},${line.idLaporUlas})" class="normal_text"/>
                                <s:hidden id="${line.idLaporUlas}kandunganUlas" name="${line.idLaporUlas}kandunganUlas"/>
                                <s:hidden name="${line.idLaporUlas}"/>
                            </td>
                            <td style="vertical-align: middle;">
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idLaporUlas},this.form,'mohonLaporUlas')"></s:button> 
                                </td>
                            </tr>

                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                    <tr>
                        <td style="text-align:center;" colspan="3">      
                            <s:button value="Tambah Ulasan" class="btn" name="simpan1"  onclick="addRow('5',this.form)"/>
                            <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="setKpsn();"/>
                            <s:hidden name="index" id="index" value="5"/>
                            <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </s:form>
</body>

