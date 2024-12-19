<%--
    Document   :  syorPPTKanan(Editable).jsp
    Created on :  March 13, 2012, 02:02:13 PM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ulasan Penolong Pegawai Tanah (Kanan)</title>
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
    }); //END OF READY FUNCTION
    
    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function addRow(index,f)
    {   
        NoPrompt();
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?tambahRow&index='+index;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function deleteRow(idKandungan,f,tName)
    {   
        NoPrompt();

        if(confirm('Adakah anda pasti untuk menghapus data ini ')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/penguatkuasaan/laporan_tanahV2?deleteRow',{idKandungan:idKandungan,tName:tName,typeName:'PPTKanan'},
            function(data){
                $('#page_div').html(data);

            }, 'html');
            self.refreshpage2('syorPPTKanan') ;
        }
    }
    
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2('main');
        self.close();
    }
        
    function setKandungan(i,idLaporUlas){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        $('#'+idLaporUlas+'kandunganUlas').val(kandungan);
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
    <s:form beanclass="etanah.view.penguatkuasaan.LaporanTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>   
        <s:hidden name="jenisLaporan" id="jenisLaporan"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>
                        <c:choose>
                            <c:when test="${actionBean.kodNegeri eq '04'}">                    
                                <legend>
                                    <c:choose>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                                            Ulasan Penolong Pegawai Tanah (Kanan) 
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                                            Ulasan Penolong Pegawai Tanah (Kanan)
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                                            Ulasan Penolong Pegawai Tanah (Kanan)
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>
                                </legend>
                            </c:when>
                            <c:when test="${actionBean.kodNegeri eq '05'}">
                                <legend>
                                    <c:choose>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                            Ulasan Penolong Pegawai Tanah (Kanan)                     
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PPBB'}">
                                            Ulasan Penolong Pegawai Tanah (Kanan) 
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTD'}">
                                            Ulasan Penolong Pegawai Tanah (Kanan)
                                        </c:when>
                                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PBPTG'}">
                                            Ulasan Penolong Pegawai Tanah (Kanan)
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>
                                </legend>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>                    
                    </legend>
                </div>
                <legend>Ulasan</legend>
                <table class="tablecloth" border="0" align="center">
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPPTKanan}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="${num})"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiLaporanKandunganPPTKanan[${i-1}].ulasan" cols="80"  rows="5" onblur="setKandungan(${i},${line.idLaporUlas})" class="normal_text"/>
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
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"/>
                            <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:hidden name="index" id="index" value="6"/>
                            <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </s:form>
</body>

