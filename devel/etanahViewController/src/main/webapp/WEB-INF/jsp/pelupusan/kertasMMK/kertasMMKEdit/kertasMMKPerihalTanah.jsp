<%-- 
    Document   : kertasMMKPerihalTanah
    Created on : Apr 16, 2013, 12:40:26 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perihal Tanah</title>
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
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function addRow(index,f)
    {   
        NoPrompt();
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?tambahRow&index='+index;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function deleteRow(idKandungan,f,tName)
    {   
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/kertas_MMKV2?deleteRow&idKandungan='+idKandungan+'&tName='+tName+'&typeName=kTanah',q,
            function(data){
                $('#page_div').html(data);

            }, 'html');
            self.refreshpage2('kTanah') ;
        }
    }
    
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2KertasMMK('main');
        self.close();
    }
        
    function setKandungan(i,idKandungan){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        $('#'+idKandungan+'kandunganKertas').val(kandungan);
    }
    
    function checkKandungan(i){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        if(kandungan == 'Sila Tambah Ayat Di Sini'){
            $('#kandungan5'+index).val("");
        }
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
    <s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>    
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Perihal Tanah</legend>
                <table class="tablecloth" align="center">
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiKandunganPerihalTanah}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="2.2.${num}"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiKandunganPerihalTanah[${i-1}].kandungan" cols="80"  rows="5" onclick="checkKandungan(${i})" onblur="setKandungan(${i},${line.idKandungan})" class="normal_text"/>
                                <s:hidden id="${line.idKandungan}kandunganKertas" name="${line.idKandungan}kandunganKertas"/>
                                <s:hidden name="idKandungan1" id="idKandungan1" value="${line.idKandungan}"/>
                            </td>
                            <td style="vertical-align: middle;">
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form,'mohonKertasKandungan')"></s:button> 
                                </td>
                            </tr>

                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                    <tr>
                        <td>2.2.${num}</td>
                        <td colspan="2">${actionBean.defaultPerihalSempadan}
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td colspan="2"><display:table class="tablecloth" name="${actionBean.laporTanahSempadanList}" cellpadding="0" cellspacing="0"
                                       requestURI="/pelupusan/draf_mmkn_pjbtr" id="line">
                                <display:column title="Kedudukan">
                                    <c:if test="${line.jenisSempadan eq 'U'}">
                                        Utara
                                    </c:if>
                                    <c:if test="${line.jenisSempadan eq 'S'}">
                                        Selatan
                                    </c:if>
                                    <c:if test="${line.jenisSempadan eq 'B'}">
                                        Barat
                                    </c:if>
                                    <c:if test="${line.jenisSempadan eq 'T'}">
                                        Timur
                                    </c:if>
                                </display:column>
                                <display:column title="Tanah Kerajaan/Rizab/Lot/PT" >
                                    <c:if test="${line.milikKerajaan eq 'T' || line.milikKerajaan eq 'N'}">
                                        <c:if test="${line.noLot ne null && line.kodLot ne null}">
                                            ${line.kodLot.nama} ${line.noLot}
                                        </c:if>
                                    </c:if>
                                    <c:if test="${line.milikKerajaan eq 'Y'}">
                                        Tanah Kerajaan
                                    </c:if>
                                    <c:if test="${line.milikKerajaan eq 'R'}">
                                        Rizab
                                    </c:if>

                                </display:column>
                                <display:column title="Apa yang terdapat di atas tanah">Tanah ini digunakan untuk ${line.guna} dan keadaannya adalah ${line.keadaanTanah}</display:column>
                            </display:table></td>
                    </tr>        
                    <tr>
                        <td style="text-align:center;" colspan="3">      
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"/>
                            <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:hidden name="indexKertas" id="indexKertas" value="3"/>
                            <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </s:form>
</body>