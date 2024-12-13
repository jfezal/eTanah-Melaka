<%-- 
    Document   : maklumat_pemohon
    Created on : Oct 23, 2009, 2:53:23 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<meta http-equiv="refresh" content="100">--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {

        $('.empty').remove();

        var len = ${fn:length(actionBean.listPemohon)};
        if(len > 0){
            $('#hapus').show();
        }else{
            $('#hapus').hide();
        }


        var len2 = $(".alamat").length;      


      <%--  for ( var i=0; i<len2; i++){
            var d = $('.x'+i).val();
            $('.nama'+i).bind('click',d, function(){               
               window.open("${pageContext.request.contextPath}/common/maklumat_pemohon?showEditPemohon&idPihak="+i, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            });

            $('.nama'+i).click( function() {
                alert(d);
                window.open("${pageContext.request.contextPath}/common/maklumat_pemohon?showEditPemohon&idHakmilik="+$('.x'+i).val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            });
        }--%>
           
    });

    function popup(f){
        var url = f.action + '?popup&idPermohonan='+$("#idP").val()+"&idHakmilik="+$("#idH").val();
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbar=1");
    }

    function addRows(nama, noPengenalan, idPihak, idPermohonan, alamat, noTelefon1, email, a){

        var rowNo = $('table#line1 tr').length;
        $('table#line1 > tbody').append("<tr id='x"+rowNo+"'><td>"+rowNo+"</td><td>"+nama+"</td><td>"+noPengenalan+"</td><td>"+alamat+"</td><td>"+noTelefon1+"</td><td>"+email+"</td><td><center>"+
                "<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem"+
                rowNo+"' onclick=\"edit('"+a+"');return false;\" onmouseover=\"this.style.cursor='pointer';\"></center></td><td><center>"+
                "<img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem' id='rem"+
                rowNo+"' onclick=\"removePemohon('"+a+"')\" onmouseover=\"this.style.cursor='pointer';\"></center></td></tr>");
        $('#idP').val(idPermohonan);
        $('#hapus').show();
        refreshPage();
    }

    function edit(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_pemohon?showEditPemohon&idPihak="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");                
    }

    function removePemohon(val,nama){
            if(confirm('Adakah pasti untuk hapus "'+nama+'" dalam senarai?')){
                var url = '${pageContext.request.contextPath}/common/maklumat_pemohon?deletePemohon&idPemohon='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/common/maklumat_pemohon?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
    }

</script>

<s:form beanclass="etanah.view.stripes.common.MaklumatPemohonActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tuan Tanah
            </legend>
            <p align="center">

                <s:button name="" style="${actionBean.display}" id="popup1" value="Cari" class="btn" onclick="popup(this.form);"/>
                &nbsp;
            </p>

            <div class="content" align="center">

                <display:table name="${actionBean.listPemohon}" id="line1" class="tablecloth" requestURI="/common/maklumat_pemohon">
                    <display:column title="Bil">
                        ${line1_rowNum}
                        <s:hidden name="x" class="x${line1_rowNum -1}" value="${line1.pihak.idPihak}"/>
                    </display:column>
                    <display:column title="Nama" property="pihak.nama"/>
                    <display:column property="pihak.noPengenalan" title="No. Pengenalan" />
                    <display:column title="Alamat" class="alamat">
                        ${line1.pihak.suratAlamat1}<c:if test="${line1.pihak.suratAlamat2 ne null}">,</c:if>
                        ${line1.pihak.suratAlamat2}<c:if test="${line1.pihak.suratAlamat3 ne null}">,</c:if>
                        ${line1.pihak.suratAlamat3}<c:if test="${line1.pihak.suratAlamat4 ne null}">,</c:if>
                        ${line1.pihak.suratAlamat4}<c:if test="${line1.pihak.suratPoskod ne null}">,</c:if>
                        ${line1.pihak.suratPoskod}<c:if test="${line1.pihak.suratNegeri.kod ne null}">,</c:if>
                        ${line1.pihak.suratNegeri.nama}
                    </display:column>
                    <display:column property="pihak.noTelefon1" title="No. Telefon" />
                    <display:column property="pihak.email" title="Alamat Email" />
                    <c:if test="${actionBean.display eq null}">
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line1_rowNum}' onclick="edit('${line1.pihak.idPihak}');return false;" onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                     id='rem_${line1_rowNum}' onclick="removePemohon('${line1.idPemohon}','${line1.pihak.nama}');" onmouseover="this.style.cursor='pointer';">
                            </div>
                        </display:column>
                    </c:if>
                </display:table>


            </div>
        </fieldset>      
    </div>        
</s:form>
