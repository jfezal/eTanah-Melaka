<%-- 
    Document   : test1
    Created on : May 27, 2011, 12:02:53 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<%--  <s:hidden name="${actionBean.senaraiRujukanLuar}" id ="sRL"/>--%>
<script type = "text/javascript">

    $(document).ready(function() {

        //        $('#buttontandatangan').hide();
    });

    function search(index) {
    <%--alert("search:"+index);--%>
        var url = '${pageContext.request.contextPath}/consent/jabatan_teknikal?kodAgensiPopup&index=' + index;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }
    function search1(kod, idRujukan) {
        //                alert("Kod Agensi :" + kod);
        //                alert("Id Rujukan :" + idRujukan);
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/consent/jabatan_teknikal?edit&kod=' + kod + '&idRujukan=' + idRujukan;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }

    function removePermohonanRujukanLuar(idRujukan, row) {
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/consent/jabatan_teknikal?deleteRujukan&idRujukan='
                    + idRujukan;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function savetolist() {
        var li1 = document.write(sRL);
        var input = document.createElement('INPUT');
        input.setAttribute("type", "text");
        input.setAttribute("name", "salinanKod");
        input.setAttribute("id", "salinanKod");
        li1.appendChild(input);

        var li2 = document.write(sRL);
        var input2 = document.createElement('INPUT');
        input2.setAttribute("type", "text");
        input2.setAttribute("name", "salinanKepada");
        input2.setAttribute("id", "salinanKepada");
        li2.appendChild(input2);
    }




    //            function kemaskini(kod) {
    //                   alert(kod) ;
    //                    var url = '${pageContext.request.contextPath}/consent/jabatan_teknikal?edit&kod=' + kod;
    //                    $.get(url,
    //                    function(data){
    //                        $('#page_div').html(data);
    //                    },'html');


    <%--   $(document).ready( function() {

            $('.agensi.nama'+i).click( function() {
                window.open("${pageContext.request.contextPath}/agensi?name="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }--%>


    function refreshAgensi() {
        var url = '${pageContext.request.contextPath}/consent/jabatan_teknikal?refreshPage';
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function test() {

        if ($('#namapguna').val() != "") {
            $('#buttontandatangan').show();
            //        alert($('#namapguna').val()) ;
            //        var ditundatangan = $('#namapguna').val() ;
            //        alert(ditundatangan) ;
            //        
            //        document.form.testing.value = ditundatangan ;
            //        var a = $('#testing').val()
            //        alert(a) ;
            //         var url = ' ${pageContext.request.contextPath} /consent/jabatan_teknikal?simpanTandatangan';
            //                    $.get(url,
            //                    function(data){
            //                        $('#page_div').html(data);
            //                    },'html');
        }

    }


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.JabatanTeknikalActionBean" name="form">
    <s:messages/>

    <fieldset class="aras1">
        <br/>
        <div class="content" align="center"> 
            <c:set value="0" var="i"/>
            <c:set value="0" var="k"/>     
            <display:table class="tablecloth" name="${actionBean.senaraiRujukanLuar}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/consent/jabatan_teknikal">

                <display:column title ="Bil" style="width:1%;">${line_rowNum}
                    <s:hidden name="" class="${line_rowNum -1}" value="${line.idRujukan}"/>
                </display:column>
                <display:column  title ="Nama Jabatan" style="width:25%;">
                    ${line.agensi.nama}
                </display:column>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMMK1' || actionBean.permohonan.kodUrusan.kod eq 'PMMK2'}">
                    <display:column  title ="Nama Pegawai (Untuk Perhatian)" style="width:26%;"><font color="red"><b>*</b></font>
                        <s:text name="senaraiRujukanLuar[${line_rowNum - 1}].catatan" maxlength="100" size="40"/>           
                    </display:column>
                </c:if>
                <display:column title ="Alamat" >
                    <font style="text-transform:uppercase;">
                    ${line.agensi.alamat1}  ${line.agensi.alamat2}  ${line.agensi.alamat3}  ${line.agensi.alamat4}  ${line.agensi.poskod}  ${line.agensi.negeri.nama}
                    </font>
                </display:column>
                <display:column  title ="Susunan" style="text-align:center;width:5%;">
                    <s:text name="senaraiRujukanLuar[${line_rowNum - 1}].nilai" maxlength="2" size="10" style="text-align:center" /> 
                </display:column>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'RMJTL'}">
                    <display:column title ="Hapus"  style="text-align:center">
                        <c:choose> 
                            <c:when test="${line.agensi.kod eq '6005' || line.agensi.kod eq '6007' || line.agensi.kod eq '6021' || line.agensi.kod eq '0302' || line.agensi.kod eq '3204'}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:if test="${actionBean.kodAgensiDefined[k] ne line.agensi.kod}">
                                    <img alt='Klik Untuk Hapus' border='0' align="middle" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removePermohonanRujukanLuar('${line.idRujukan}', '${i}')"  onmouseover="this.style.cursor = 'pointer';"/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </display:column>
                </c:if>
                <c:set var="k" value="${k+1}" />
                <s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />
            </display:table>
            <p>
                <c:if test="${fn:length(actionBean.senaraiRujukanLuar) > 0}">
                    <s:button name="simpanKekananan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'RMJTL'}">
                    <s:button name="add" id="tambah" value="Tambah" class="btn" onclick="javascript:search(${i})"/>
                </c:if>
            </p>
        </div>

    </fieldset>
</s:form>





