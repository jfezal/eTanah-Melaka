<%-- 
    Document   : maklumat_Kehadiran_NEW
    Created on : Jul 9, 2010, 11:39:42 AM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">
    function tambahBaru(val,id){
        window.open("${pageContext.request.contextPath}/lelong/maklumat_kehadiran_NEW?tambahBarule&idPihak="+val +"&id="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400");
    }

    function save1(event, f){
        alert('hohoho');
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);

            setTimeout(function(){
                self.close();
            }, 100);
        },'html');
    }

    $(document).ready(function() {

        var bil = $(".nama").length;
        for (var i = 0; i < bil; i++){
            $('#ada'+i).attr("disabled", "true");
            $('#tiada'+i).attr("disabled", "true");
            $('#edit'+i).hide();
            $('#catatan'+i).hide();
            $('#keterangan'+i).hide();
            $('#remove'+i).hide();
        }
        var pg = $('#pg').val();
        var pyh = $('#penyerah').val();
        if(pg == "N" && pyh == "N"){
            $('#kosKehadiran').show();
        }else{
            $('#kosKehadiran').hide();
        }
    });

    function changeLain(value,row,id){
        
          if(id == "pg"){
            $('#pg').val(value);
            var pyh = $('#penyerah').val();
            if(value == "N" && pyh == "N"){
                $('#kosKehadiran').show();
               // alert("Sila Masukkan Kos Kehadiran");
                $('#idKosKehadiran').focus();
            }else{
                $('#kosKehadiran').hide();
            }
        }
        if(id == "penyerah"){
            $('#penyerah').val(value);
            var pg = $('#pg').val();
            if(value == "N" && pg == "N"){
                $('#kosKehadiran').show();
               // alert("Sila Masukkan Kos Kehadiran");
                $('#idKosKehadiran').focus();
            }else{
                $('#kosKehadiran').hide();
            }
        }
        
        if(value=="Y"){
            $('#edit'+row).show();
        }else if(value=="N"){
            $('#edit'+row).show();
        }
        else{
            $('#edit'+row).hide();
        }
    }

    function validate(){


        var bil = $(".nama").length;

        for (var i = 0; i < bil; i++){
            var ya = document.getElementById('ya'+i);
            var tidak = document.getElementById('tidak'+i);
            if(ya.checked == false && tidak.checked == false ){
                alert("Sila Pilih Kehadiran");
                $('#ya'+i).focus();
                return false;
            }
        }
       // var pg = $('#pg').val();
      //  var pyh = $('#penyerah').val();
      //  if(pg == "N" && pyh == "N"){
           // var kos = $('#idKosKehadiran').val();
           // if(kos == ""){
              //  alert("Sila Masukkan Kos Kehadiran");
              //  $('#idKosKehadiran').focus();
              //  return false;
            //}
        //}
        

        return true;
    }


    function cacat(row) {

        var tggle='on';
        var obj;

        obj=document.getElementById('rem'+row);
        document.getElementById('ulasan'+row).onclick=function(){
            if (tggle=='on'){
                obj.src="${pageContext.request.contextPath}/pub/images/segi2.gif"
                tggle='off';
                $('#catatan'+row).toggle(1000);
            }
            else {
                obj.src="${pageContext.request.contextPath}/pub/images/segi1.gif"
                tggle='on';
                $('#catatan'+row).toggle(1000);
            }
            
            return false;
        }
    }

    function cacat2(row){
        var tggle='on';
        var obj;

        obj=document.getElementById('rem2'+row);
        document.getElementById('kete'+row).onclick=function(){
            if (tggle=='on'){
                obj.src="${pageContext.request.contextPath}/pub/images/segi2.gif"
                tggle='off';
            }
            else {
                obj.src="${pageContext.request.contextPath}/pub/images/segi1.gif"
                tggle='on';
            }
            $('#keterangan'+row).toggle(1000);
            return false;
        }
    }

    function output()
    {
        alert("Sila Masukkan Maklumat Wakil Jika Ada Wakil Yang Hadir");
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/maklumat_kehadiran_NEW?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function removeWakil(val,id){
        if(confirm('Adakah anda pasti?')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/lelong/maklumat_kehadiran_NEW?deleteWakil&idKehadiran='+val+'&id='+id;
            $.get(url,
            function(data){
                $('#maklumatWakil'+data).empty();
                $('#remove'+data).hide();
            },'html');
            $.unblockUI();
        }
    }
    
    function validateKos() {

        var num = document.getElementById('idKosKehadiran').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#idKosKehadiran').val((((sign)?'':'-') + num + '.' + cents));
    }
    
</script>

<s:form beanclass="etanah.view.stripes.lelong.MaklumatKehadiranNEWActionBean" id="kehadiran">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle" align="center">
        <fieldset class="aras1">

            <legend>
                Penggadai/Pihak Berkepentingan
            </legend>
            <br><br>
            <display:table class="tablecloth" name="${actionBean.listHakmilikPihakBerkepentingan}"  cellpadding="0" cellspacing="0" requestURI="/lelong/maklumat_kehadiran_NEW" id="line">
                <display:column title="Bil" class="rowLine" sortable="true" >${line_rowNum}</display:column>
                <display:column title="Nama" property="pihak.nama" style="text-transform:uppercase;"/>
                <display:column title="IDHakmilik" property="hakmilik.idHakmilik"/>
                <display:column title="No.Pengenalan" property="pihak.noPengenalan" class="${line_rowNum}" style="text-transform:uppercase;"/>
                <display:column title="Status" property="jenis.nama" class="${line_rowNum}" style="text-transform:uppercase;"/>
            </display:table><br>
        </fieldset>
        <fieldset class="aras1">

            <legend>
                Senarai Kehadiran
            </legend><br><br>

            <display:table class="tablecloth" name="${actionBean.listKehadiran}"  cellpadding="0" cellspacing="0" requestURI="/lelong/maklumat_kehadiran_NEW" id="line">
                <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                <display:column title="Nama" class="nama" style="text-transform:uppercase;">
                    <c:if test="${line.idPenyerah eq null}">
                        ${line.hakmilikPihakBerkepentingan.pihak.nama}
                    </c:if>
                    <c:if test="${line.idPenyerah ne null}">
                        ${actionBean.peguam.nama}
                    </c:if>
                </display:column>
                <%--<display:column title="IDHakmilik">
                    <c:if test="${line.idPenyerah eq null}">
                        ${line.hakmilikPihakBerkepentingan.hakmilik.idHakmilik}
                    </c:if>
                    <c:if test="${line.idPenyerah ne null}">
                        -
                </c:if>
                </display:column>--%>
                <display:column title="Status" class="${line_rowNum}" style="text-transform:uppercase;">
                    <c:if test="${line.idPenyerah eq null}">
                        <c:if test="${line.permohonanPihak ne  null}">
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod ne 'PG'}">
                                Penggadai
                            </c:if>
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod eq 'PG'}">
                                ${line.hakmilikPihakBerkepentingan.jenis.nama}
                            </c:if>
                        </c:if>
                        <c:if test="${line.permohonanPihak eq null}">
                            ${line.hakmilikPihakBerkepentingan.jenis.nama}
                        </c:if>
                    </c:if>
                    <c:if test="${line.idPenyerah ne null}">
                        Peguam
                    </c:if>
                </display:column>
                <display:column title="Kehadiran" class="${line_rowNum}">
                    <div align="left">
                        <c:if test="${line.idPenyerah eq null}">
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod eq 'PG'}">

                                <c:if test="${line.hadir eq null}" >
                                    <input type="radio" name="rb${line_rowNum - 1}" value="Y" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'pg');"/>Ya<br>
                                    <input type="radio" name="rb${line_rowNum - 1}" value="N" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'pg');"/>Tidak
                                    <s:hidden name="pg" id="pg"/>
                                </c:if>
                                <c:if test="${line.hadir ne null && line.hadir eq 'Y'}">
                                    <input type="radio" name="rb${line_rowNum - 1}" value="Y" checked="checked" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'pg');"/>Ya<br>
                                    <input type="radio" name="rb${line_rowNum - 1}" value="N" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'pg');"/>Tidak
                                    <s:hidden name="pg" id="pg" value="Y"/>
                                </c:if>
                                <c:if test="${line.hadir ne null && line.hadir eq 'N'}">
                                    <input type="radio" name="rb${line_rowNum - 1}" value="Y" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'pg');"/>Ya<br>
                                    <input type="radio" name="rb${line_rowNum - 1}" value="N" checked="checked" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'pg');"/>Tidak
                                    <s:hidden name="pg" id="pg" value="N"/>
                                </c:if>
                            </c:if>
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod ne 'PG'}">
                                <c:if test="${line.hadir eq null}" >
                                    <input type="radio" name="rb${line_rowNum - 1}" value="Y" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'');"/>Ya<br>
                                    <input type="radio" name="rb${line_rowNum - 1}" value="N" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'');"/>Tidak
                                </c:if>
                                <c:if test="${line.hadir ne null && line.hadir eq 'Y'}">
                                    <input type="radio" name="rb${line_rowNum - 1}" value="Y" checked="checked" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'');"/>Ya<br>
                                    <input type="radio" name="rb${line_rowNum - 1}" value="N" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'');"/>Tidak
                                </c:if>
                                <c:if test="${line.hadir ne null && line.hadir eq 'N'}">
                                    <input type="radio" name="rb${line_rowNum - 1}" value="Y" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'');"/>Ya<br>
                                    <input type="radio" name="rb${line_rowNum - 1}" value="N" checked="checked" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'');"/>Tidak
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${line.idPenyerah ne null}">
                            <c:if test="${line.hadir eq null}" >
                                <input type="radio" name="rb${line_rowNum - 1}" value="Y" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'penyerah');"/>Ya<br>
                                <input type="radio" name="rb${line_rowNum - 1}" value="N" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'penyerah');"/>Tidak
                                <s:hidden name="penyerah" id="penyerah"/>
                            </c:if>
                            <c:if test="${line.hadir ne null && line.hadir eq 'Y'}">
                                <input type="radio" name="rb${line_rowNum - 1}" value="Y" checked="checked" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'penyerah');"/>Ya<br>
                                <input type="radio" name="rb${line_rowNum - 1}" value="N" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'penyerah');"/>Tidak
                                <s:hidden name="penyerah" id="penyerah" value="Y"/>
                            </c:if>
                            <c:if test="${line.hadir ne null && line.hadir eq 'N'}">
                                <input type="radio" name="rb${line_rowNum - 1}" value="Y" id="ya${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'penyerah');"/>Ya<br>
                                <input type="radio" name="rb${line_rowNum - 1}" value="N" checked="checked" id="tidak${line_rowNum - 1}" onclick="changeLain(this.value,${line_rowNum-1},'penyerah');"/>Tidak
                                <s:hidden name="penyerah" id="penyerah" value="N"/>
                            </c:if>
                        </c:if>
                    </div>
                </display:column>
                <display:column title="Wakil/Hadir Bersama">
                    <c:if test="${line.wakilNama eq null}">
                        <div id="maklumatWakil${line_rowNum-1}">-</div>
                        <div id="edit${line_rowNum - 1}" >
                            <ul>
                                <li>
                                    <a id="" onclick="tambahBaru('${line.idKehadiran}','${line_rowNum-1}');return false;" onmouseover="this.style.cursor='pointer';">
                                        <img alt="Sila Klik Untuk Masukan Maklumat Wakil/Hadir Bersama" src='${pageContext.request.contextPath}/pub/images/edit.gif'
                                             title="Sila Klik Untuk Masukan Maklumat Wakil/Hadir Bersama"/>
                                    </a>
                                    <div id="remove${line_rowNum - 1}"> <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeWakil('${line.idKehadiran}','${line_rowNum-1}');"
                                                                             title="Klik Untuk Hapus"/></div>
                                </li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${line.wakilNama eq null && line.hadir eq 'N'}">
                        Tiada Wakil
                    </c:if>

                    <c:if test="${line.wakilNama ne null}">
                        <div id="maklumatWakil${line_rowNum-1}">
                            ${line.wakilNama}
                        </div>
                        <div align="center">

                            <a id="" onclick="tambahBaru('${line.idKehadiran}','${line_rowNum-1}');return false;" onmouseover="this.style.cursor='pointer';">
                                <img alt="Sila Klik Untuk Masukan Maklumat Wakil/Hadir Bersama" src='${pageContext.request.contextPath}/pub/images/edit.gif'
                                     title="Sila Klik Untuk Masukan Maklumat Wakil/Hadir Bersama"/>
                            </a>

                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeWakil('${line.idKehadiran}','${line_rowNum-1}');"
                                 title="Klik Untuk Hapus"/>
                        </div>

                    </c:if>
                </display:column>

                <display:column title="Catatan" class="${line_rowNum}" style="text-transform:uppercase;">
                    <%--<ul>
                        <li><a id="ulasan${line_rowNum - 1}" onclick="javascript:cacat('${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                <img alt="Sila Klik Untuk Masukkan Catatan"  src='${pageContext.request.contextPath}/pub/images/segi1.gif' id='rem${line_rowNum -1}' onmouseover="this.style.cursor='pointer';" width=10 height=10 /> Ulasan</a>
                            <ul id="catatan${line_rowNum - 1}">
                                <li><s:textarea id="catat${line_rowNum - 1}" name="catatkan${line_rowNum - 1}" cols="40" rows="5">${line.catatan}</s:textarea></li>
                            </ul>
                        </li>
                    </ul>--%>

                    <ul>
                        <li><a title="Sila Klik Untuk Masukkan Keterangan" id="kete${line_rowNum - 1}" onclick="javascript:cacat2('${line_rowNum - 1}');return false;" onmouseover="this.style.cursor='pointer';">
                                <img alt="Sila Klik Untuk Masukkan Keterangan" src='${pageContext.request.contextPath}/pub/images/segi1.gif' id='rem2${line_rowNum -1}'width=10 onmouseover="this.style.cursor='pointer';" height=10
                                     title="Sila Klik Untuk Masukkan Keterangan" />Keterangan</a>
                            <ul id="keterangan${line_rowNum - 1}">
                                <li><s:textarea id="keterang${line_rowNum - 1}" name="terangan${line_rowNum - 1}" cols="50" rows="6">${line.keterangan}</s:textarea></li>
                            </ul>
                        </li>
                    </ul>
                </display:column>

            </display:table><br>


            <p id="kosKehadiran" align="left">
                <label><font></font> Kos Kehadiran : </label>
                RM <s:text name="enkuiri.kosKehadiran" id="idKosKehadiran" onblur="validateKos();"  formatPattern="#,##0.00"></s:text>
            </p>
            <br>
            <br>
            <p align="right"><label></label>
                <s:button name="save" id="save" value="Simpan" class="btn"  onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
            </p>
            <br>
        </fieldset>

    </div>
</s:form>