<%-- 
    Document   : penangguhan_gadaian
    Created on : Aug 5, 2011, 3:35:06 PM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function() {
        var len = $(".hakmilik").length;

        for (var i=0; i<=len; i++){
            $('.perserahan'+i).click( function() {
                window.open("${pageContext.request.contextPath}/common/maklumat_permohonan?viewPermohonan&idPermohonan="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
    

    function doDisableB(id) {
       var len = $('.cbB').length;
       for (var i=1;i<=len;i++) {
           $('#chkbox_b' + i).removeAttr('disabled');
       }
       $('#chkbox_b' + id).attr('disabled', 'true');
    }

    function doDisableA(id) {
       var len = $('.cbA').length;
       for (var i=1;i<=len;i++) {
           $('#chkbox_a' + i).removeAttr('disabled');
       }
       $('#chkbox_a' + id).attr('disabled', 'true');
    }

    function doPopup(val) {
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?viewPermohonan&idPermohonan=' + val;
        window.open(url, "eTanah", strWindowFeatures);
    }
    function remove(val){
        if(confirm ('Adakah Anda Pasti?')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/daftar/gadaian?delete&id='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');
        }
    }

    function removeSelect(id) {

        if (confirm('adakah anda pasti?')) {
            var param = '';
            var len = $('.remove').length;
            doBlockUI();
            for(var i=1; i<=len; i++){

                var ckd = $('#rm_' + id + '_'+i).is(":checked");
                if(ckd){
                    param = param + '&uids=' + $('#rm_'+ id + '_'+i).val();
                }
            }

            if(param == ''){
                alert('Tiada urusan dipilih.');
                doUnBlockUI();
                return;
            }

            var url = '${pageContext.request.contextPath}/daftar/gadaian?deleteSelectedItem'+ param+'&idHakmilik=' + $('#hakmilik').val();

                $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function (xhr, ajaxOptions, thrownError){
                    alert("error=" + xhr.responseText);
                    doUnBlockUI();
                },
                success : function(data){
                    $('#page_div').html(data);
                    //doPopupMsg("Kemaskini berjaya!");
                    doUnBlockUI();
                }
            });
        }
    }    

    function selectAll(a){        
        for (i = 1; i < 100; i++){
            if (a.id == 'choose1' ) {
                var c = document.getElementById("chkbox_a" + i);
                if (c == null) break;
                c.checked = a.checked;
            } else if (a.id == 'choose2' ) {
                var c = document.getElementById("chkbox_b" + i);
                if (c == null) break;
                c.checked = a.checked;
            }
            else if (a.id == 'deleteA' ) {
                var c = document.getElementById("rm_a_" + i);
                if (c == null) break;
                c.checked = a.checked;
            }
            else if (a.id == 'deleteB' ) {
                var c = document.getElementById("rm_b_" + i);
                if (c == null) break;
                c.checked = a.checked;
            }
        }
    }

    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/gadaian?searchPenangguhanGadaian&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                $('.popup').each(function(){
                        $(this).html('<u>' + $(this).text() + '</u>');
                        $(this).mouseover(function(){
                            $(this).addClass("cursor_pointer");
                        });
                    });
                var len = $(".hakmilik").length;

                for (var i=0; i<=len; i++){
                    $('.perserahan'+i).click( function() {
                        window.open("${pageContext.request.contextPath}/common/maklumat_permohonan?viewPermohonan&idPermohonan="+$(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
                    });
                }
                doUnBlockUI();
            }
        });
    }

</script>

<s:errors/>
<s:messages/>
<s:form beanclass="etanah.view.daftar.Gadaian">    
    <div class="subtitle">
         <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
             <p><font color="red">Sila Pilih Hakmilik Yang Terlibat.</font></p>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br/>
        </fieldset>
        <fieldset class="aras1">
            <legend>
                Senarai Gadaian
            </legend>
            <br/>
            <p><font color="red">Sila Pilih Pada Rekod Yang Berkenaan dan Tekan Butang Simpan.</font></p>

            <div class="content" align="center">               
                <%--<display:table class="tablecloth" name="${actionBean.senaraiList}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                    <display:column title="Tangguh">
                        <s:radio name="tangguh" id="chkbox_a${line_rowNum}"
                                 value="${line.idPermohonan}" class="cbA"
                                 onclick="doDisableB('${line_rowNum}');"/>
                    </display:column>
                    <display:column title="Selepas">
                        <s:radio name="lepas" id="chkbox_b${line_rowNum}"
                                 value="${line.idPermohonan}" class="cbB"
                                 onclick="doDisableA('${line_rowNum}');"/>
                    </display:column>
                    
                    <display:column property="idPermohonan" title="ID Perserahan" class="popup perserahan${line_rowNum}"/>
                    <display:column title="Hakmilik Terlibat" class="hakmilik">
                        <c:forEach items="${line.senaraiHakmilik}" var="item">
                            <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item2">
                                <c:if test="${item.hakmilik.idHakmilik eq item2.hakmilik.idHakmilik}">
                                    ${item.hakmilik.idHakmilik}
                                    <s:hidden name="hakmilikTerlibat" value="${item.hakmilik.idHakmilik}"/>
                                    <br/>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </display:column>
                    <display:column property="kodUrusan.nama" title="Urusan"/>
                </display:table>--%>
                <!--azmi-->
                 <display:table class="tablecloth" name="${actionBean.senaraiList}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                    <display:column title="Tangguh">
                        <s:checkbox name="tangguh" id="chkbox_a${line_rowNum}"
                                 value="${line.idPermohonan}" class="cbA"/>
                    </display:column>
                    <display:column title="Selepas">
                        <s:checkbox name="lepas" id="chkbox_b${line_rowNum}"
                                 value="${line.idPermohonan}" class="cbB"/>
                    </display:column>
                    <%--<display:column title="No" sortable="true">${line_rowNum}</display:column>--%>
                    <display:column property="idPermohonan" title="ID Perserahan" class="popup perserahan${line_rowNum}"/>
                    <display:column title="Hakmilik Terlibat" class="hakmilik">
                        <c:forEach items="${line.senaraiHakmilik}" var="item">
                            <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item2">
                                <c:if test="${item.hakmilik.idHakmilik eq item2.hakmilik.idHakmilik}">
                                    ${item.hakmilik.idHakmilik}
                                    <s:hidden name="hakmilikTerlibat" value="${item.hakmilik.idHakmilik}"/>
                                    <br/>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </display:column>
                    <display:column property="kodUrusan.nama" title="Urusan"/>
                </display:table>
                <!--azmi-->
            </div>
            <c:if test="${fn:length(actionBean.senaraiList) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="savePenangguhanGadaian"
                              id="add" value="Simpan" class="btn"
                              onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>
                Senarai Gadaian Tangguh
            </legend>

            <div class="content" align="center">              
                 <display:table class="tablecloth" name="${actionBean.senaraiGadaianTangguh}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                    <display:column title="<input type='checkbox' name='semua' id='deleteA' onclick='javascript:selectAll(this);'/>">
                        <s:checkbox name="checkbox" id="rm_a_${line_rowNum}" value="${line.idPermohonanHubungan}" class="remove"/>
                    </display:column>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="permohonanSasaran.kodUrusan.nama" title="Urusan"/>
                    <display:column property="permohonanSasaran.idPermohonan" title="ID Perserahan"/>
                </display:table>
                <br/>
                <c:if test="${fn:length(actionBean.senaraiGadaianTangguh) > 0}">
                    <s:button name="delete" onclick="removeSelect('a');" value="Hapus" class="btn"/>&nbsp;
                </c:if>
            </div>
        </fieldset>

        <br/>
        <fieldset class="aras1">
            <legend>
                Senarai Gadaian Selepas
            </legend>

            <div class="content" align="center">
                 <display:table class="tablecloth" name="${actionBean.senaraiGadaianLepas}" pagesize="10" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/common/pihak" id="line">
                    <display:column title="<input type='checkbox' name='semua' id='deleteB' onclick='javascript:selectAll(this);' />">
                        <s:checkbox name="checkbox" id="rm_b_${line_rowNum}" value="${line.idPermohonanHubungan}" class="remove"/>
                    </display:column>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="permohonanSasaran.kodUrusan.nama" title="Urusan"/>
                    <display:column property="permohonanSasaran.idPermohonan" title="ID Perserahan"/>
                </display:table>
                <br/>
                <c:if test="${fn:length(actionBean.senaraiGadaianLepas) > 0}">
                    <s:button name="delete" onclick="removeSelect('b');" value="Hapus" class="btn"/>&nbsp;
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>