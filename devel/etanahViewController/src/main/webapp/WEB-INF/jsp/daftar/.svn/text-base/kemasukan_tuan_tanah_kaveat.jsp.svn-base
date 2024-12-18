<%--
    Document   : kemasukan_senarai_tuan_tanah
    Created on : 15-Oct-2009, 03:41:03
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.empty').remove();

        var len2 = $(".alamat").length;

        for ( var i=0; i<len2; i++){
            var d = $('.x'+i).val();

            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            });
        }

        $('.rem').each(function(){
            $(this).mouseover(function(){
                $(this).addClass("cursor_pointer");
            });
        });
    
    });

    function popupExisting(){
        window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function addNew(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup&tuanTanah=true", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=600");
    }

    function addNew2(){
        window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function copy(){
        $('#page_div').html('');
        var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }


    function addRowMohonPihak(nama, kp ,syer){
        //TODO: to be complete
        var rowNo = $('table#lineMP tr').length;
        $('table#lineMP > tbody').append("<tr id='x"+rowNo+"' class='x'><td class='rowNo'>"+rowNo
            +"</td><td>"+nama+"</td><td>"+kp+"</td><td>"+syer+"</td><td>"+
            "<img alt='Click to Delete' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem"+
            rowNo+"' onclick=\"remove(this.id,'line')\"></td></tr>");
    }

    function remove(val){
        if(confirm('Adakah anda pasti?')){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
                
            var url = '${pageContext.request.contextPath}/common/permohonan_atas_pihak?deleteMohonAtasPihakKaveat&id='+val;
    <%--$.get(url,
    function(data){
        $('#page_div').html(data);
    },'html');--%>
                $.ajax({
                    type:"POST",
                    url : url,
                    dataType : 'html',
                    error : function(xhr, ajaxOptions, thrownError) {
                        //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                        $("#page_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t></div><br/><div id=error class=b>' + xhr.responseText + '</div>');
                        $.unblockUI();
                    },
                    success : function(data) {
                        $('#page_div').html(data);
                        $.unblockUI();
                    }
                });
             

            }
        }

        function removePemohon(val){
            var url = '${pageContext.request.contextPath}/pihak_berkepentingan?deletePemohon&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function addPemohon(){
            var len = $('.nama').length;
            for(var i=1; i<=len; i++){
                var ckd = $('#chkbox'+i).is(":checked");
                if(ckd){
                    var url = '${pageContext.request.contextPath}/pihak_berkepentingan?simpanPemohon&idPihak='+$('#chkbox'+i).val();
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    });
                }
            }
        }

        function semakSyer(f){
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pihak_berkepentingan?semakSyer',q,
            function(data){
                if(data != ''){
                    alert(data);
                }
            }, 'html');
        }

        function dopopup(i){
            var d = $('.x'+i).val();
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
        }


</script>
<div class="subtitle displaytag">
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.common.PermohonanAtasPihakBerkepentinganActionBean">

         <fieldset class="aras1">
            <legend>Senarai Pemilik</legend>
            <p align="center">
                    <label></label>&nbsp;
                    <display:table class="tablecloth" name="${actionBean.othersPihakList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">                        
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama" class="nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="hakmilik.idHakmilik" title="Hakmilik Terlibat" />
                        <display:column title="Bahagian yang dimiliki" >
                        <div align="center">
                            ${line.syerPembilang}/${line.syerPenyebut}
                        </div>
                        </display:column>
                        <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                    </display:table>
                </p>
                <br/>
        </fieldset>

        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan</legend>
            <p align="center">
                    <label></label>&nbsp;
                    <display:table class="tablecloth" name="${actionBean.pihakKepentinganList}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                        <display:column ><s:checkbox name="rb" value="${line.idHakmilikPihakBerkepentingan}"/></display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="pihak.nama" title="Nama" class="nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column property="hakmilik.idHakmilik" title="Hakmilik Terlibat" />
                        <display:column title="Bahagian yang dimiliki">
                        <div align="center">
                            ${line.syerPembilang}/${line.syerPenyebut}
                        </div>
                        </display:column>
                        <display:column title="Tarikh Pemilikan Tanah"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                        <display:column property="jenis.nama" title="Jenis Pihak"/>
                    </display:table>
                </p>
                <br/>
                <c:if test="${fn:length(actionBean.pihakKepentinganList) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button class="btn" name="saveAtasPihakMultiple" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div');"/>
                </p>
                </c:if>

        </fieldset>
            <fieldset class="aras1">
            <legend>Senarai <c:if test="${actionBean.p.kodUrusan.kod ne 'PNPA'}">Pihak Yang Terlibat</c:if><c:if test="${actionBean.p.kodUrusan.kod eq 'PNPA'}">Pemegang Amanah</c:if></legend>
                <div class="content">
                    <p align="center"><label></label>&nbsp;
                        <display:table class="tablecloth" name="${actionBean.mapList}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column property="pihakBerkepentingan.pihak.nama" title="Nama" class="nama"/>                            
                            <display:column property="pihakBerkepentingan.pihak.noPengenalan" title="Nombor Pengenalan" />
                            <display:column property="pihakBerkepentingan.hakmilik.idHakmilik" title="Hakmilik Terlibat"/>
                            <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onclick="remove('${line.idAtasPihak}')"/>
                            </div>
                        </display:column>
                    </display:table>
                    </p>                    
                </div>
            </fieldset>
        <br>   
    </s:form>
</div>
