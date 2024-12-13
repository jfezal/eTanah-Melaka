<%-- 
    Document   : kemasukan_perintah_partial
    Created on : Aug 16, 2010, 2:40:53 PM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy',
            changeMonth: true,
            changeYear: true});

        $("#page_div input:text").each( function(el) {
            $(this).focus(function () {
                $(this).addClass("focus");
            });
            $(this).blur( function() {
                $(this).removeClass("focus");
            });
            if(!$(this).hasClass('normal_text')){
                $(this).blur( function() {
                    $(this).val( $(this).val().toUpperCase());
                });
            }
        });

        $("#page_div textarea").each( function(el) {
            $(this).focus(function () {
                $(this).addClass("focus");
            });
            $(this).blur( function() {
                $(this).removeClass("focus");
            });
            if(!$(this).hasClass('normal_text')){
                $(this).blur( function() {
                    $(this).val( $(this).val().toUpperCase());
                });
            }
        });
      
    });

    function doselectOrder(){
        var v = $('#order').val();
        if(v == 'MK' || v == ''){

            $('.mahkamah').show();
        }else{
            $('.mahkamah').hide();
        }
    }

    function doCalcEndDate(id){
        var hari = parseInt($('.hari').val(),10);
        var bln = parseInt($('.bulan').val(),10);
        var thn = parseInt($('.tahun').val(),10);
        if(isNaN(hari) && isNaN(bln) && isNaN(thn)){
            alert('Sila Masukan Tempoh.');
            $(id).val('');
            return;
        }
        var startDate = $('#' +id).val();
        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(6, 10), 10);
        var m = parseInt(startDate.substring(3, 5),10);
        var d = parseInt(startDate.substring(0, 2),10);
        var r = new Date();
     
        if(!isNaN(hari)){
            d = d + hari;
            //                 alert(d);
            d = d - 1;
            if (d == 0){
                m = m-1;
            }
        }
        if(!isNaN(bln)){
            //alert(bln);
            m = m + bln;
            //alert(m);

            if(m > 12){
                y = y +1;
                m = m - 12;
                if (m == 2){
                    var isleap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
                    if (d>=30){
                        if (isleap) {
                            d = 28;
                        } else {
                            d = 27;
                        }
                    }else if (d ==0 ){
                        if (isleap) {
                            d = 29;
                        } else {
                            d = 28;
                        }
                    }
                }
            }
        }
        if(!isNaN(thn)){
            y = y + thn;
        }
        //y = y + thn;
        var endDate = new Date();
        endDate.setDate(d);
        endDate.setMonth(m-1);
        endDate.setFullYear(y);
        $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
    }

    function doSave(id, form, event) {
        var q = $(form).formSerialize();        
        var url = form.action + '?' + event + '&idHakmilik=' + id;
        doBlockUI();    
        $.ajax({
            url : url,
            dataType : 'html',
            data : q,
            success : function(data) {$('#div_content').html(data);doUnBlockUI();}
        });
    }

    function doBlockUI () {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }

    function doUnBlockUI (){
        $.unblockUI();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.Kaveat">
    <s:hidden name="permohonanRujukanLuar.idRujukan"/>    
    <div class="">
        <fieldset class="aras1">
            <legend>
                Kemasukan Borang
            </legend>
            <br/>
            &nbsp;&nbsp;<font color="black  " size="2">Sila isi maklumat yang diperlukan sahaja.</font>
            <br/>
            <br/>            
            <c:if test="${empty perintahMahkamah}">
                <p>
                    <label for="nama pihak">Jenis Perintah :</label>
                    <s:select name="permohonanRujukanLuar.kodPerintah.kod" id="order" onchange="" style="width:200px">
                        <s:option value="">Sila Pilih</s:option>
                        <c:forEach items="${listUtil.senaraiKodPerintah}" var="items">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMAMEGGG'}">
                                    <c:if test="${items.kod ne 'MK'}">
                                        <s:option value="${items.kod}">${items.nama}</s:option>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${items.kod ne 'PP'}">
                                        <s:option value="${items.kod}">${items.nama}</s:option>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <%--<s:options-collection collection="${listUtil.senaraiKodPerintah}" value="kod" label="nama"/>--%>
                    </s:select>
                </p>
            </c:if>
            <c:if test="${!empty perintahMahkamah}">
                <p>
                    <label for="nama pihak">Jenis Perintah :</label>
                    Perintah Mahkamah
                    <s:hidden name="mahkamah"/>
                </p>
            </c:if>
            <p>
                <label for="nama pihak">No Fail :</label>
                <s:text name="permohonanRujukanLuar.noFail" style="width:300px"/>
            </p>
            <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNPA'}">
                    <label for="nama pihak">No Surat Amanah :</label>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PNPA'}">
                    <label for="nama pihak">No Perintah :</label>
                </c:if>
                <s:text name="permohonanRujukanLuar.noRujukan" style="width:300px"/>
            </p>
            <p class="mahkamah">
                <label for="nama pihak" >No Mahkamah :</label>
                <s:text name="permohonanRujukanLuar.noSidang" style="width:300px"/>
            </p>
            <p class="mahkamah">
                <label for="nama pihak">Nama Mahkamah :</label>
                <s:text name="permohonanRujukanLuar.namaSidang" style="width:420px"/>
            </p>
            <p class="mahkamah">
                <label for="nama pihak">Tarikh Perintah :</label>
                <s:text name="tkhSidang" class="datepicker"/>
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TMAME'}">
                <p>
                    <label for="nama pihak">Tarikh Perintah :</label>
                    <s:text name="tkhSidang" class="datepicker"/>
                </p>
            </c:if>

            <p>
                <label>Sebab Perintah :</label>
                <s:textarea name="permohonanRujukanLuar.ulasan" rows="10" cols="50"/>
            </p>

            <p>
                <label for="nama pihak">Tempoh :</label>
                <s:text name="permohonanRujukanLuar.tempohTahun" class="tahun"/> tahun &nbsp;
                <s:text name="permohonanRujukanLuar.tempohBulan" class="bulan"/> bulan &nbsp;
                <s:text name="permohonanRujukanLuar.tempohHari" class="hari"/> hari

            </p>
            <p class="mahkamah">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNPA'}">
                    <label for="nama pihak">Tarikh Rujukan :</label>
                    <s:text name="tkhRujukan" id="tkhRujukan" class="datepicker" onchange="doCalcEndDate(this.id);"/>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PNPA'}">
                    <label for="nama pihak">Tarikh Kuatkuasa :</label>
                    <s:text name="tkhKuatKuasa" id="tkhKuatKuasa" class="datepicker" onchange="doCalcEndDate(this.id);"/>
                </c:if>

            </p>

            <p class="mahkamah">

                <label for="nama pihak">Tarikh Tamat :</label>

                <s:text name="tkhTamat" id="tkhTamat" class="datepicker"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <font color="red" size="2">
                    <c:set var="checked" value=""/>
                    <c:if test="${copy_all eq 'true'}">
                        <c:set var="checked" value="checked"/>
                    </c:if>
                    <input type="checkbox" name="copyToAllHakmilik" value="1" ${checked}/>
                        Sila klik jika maklumat perintah sama untuk semua hakmilik.
                    </font>
                </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="savePerintah" value="Simpan" class="btn" onclick="doSave('${idHakmilik}', this.form, this.name);"/>
            </p>
        </fieldset>
    </div>
</s:form>
