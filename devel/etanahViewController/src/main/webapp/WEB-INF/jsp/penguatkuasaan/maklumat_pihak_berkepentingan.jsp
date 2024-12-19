<%--
    Document   : maklumat_pihak_berkepentingan
    Created on : April 11, 2013, 10:56:37 AM
    Author     : latifah.iskak
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<script type="text/javascript">
    
    
    $(document).ready(function() {

        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
        
        // to display selected pihak at first table instead of lets its empty
        
        var bilPilih= 0;
        var bilDisable = 0;
        var bil =  ${fn:length(actionBean.hakmilikPermohonanList)}; //list hakmilik permohonan list
        for (var i = 0; i < bil; i++){
            var bilLoop =  document.getElementById('valLoop'+i).value;
            var bilLoopTotal =  document.getElementById('valLoopTotal'+i).value;
            //            alert("bil : "+i);
            
            if(bilLoop != 0){
                bilDisable++;
            }
            if(bilLoop != bilLoopTotal){
                //                alert("tak sama :: displayListDiv"+i);
                document.getElementById("displayNamaListDiv"+i).style.visibility = 'visible';
                document.getElementById("displayNamaListDiv"+i).style.display = '';
                //div no.pengenalan
                document.getElementById("displayNoListDiv"+i).style.visibility = 'visible';
                document.getElementById("displayNoListDiv"+i).style.display = '';
                //div jenis PB
                document.getElementById("displayJenisListDiv"+i).style.visibility = 'visible';
                document.getElementById("displayJenisListDiv"+i).style.display = '';
                //div syer
                document.getElementById("displaySyerListDiv"+i).style.visibility = 'visible';
                document.getElementById("displaySyerListDiv"+i).style.display = '';
                //div jenis pihak
                document.getElementById("displayJenisPihakListDiv"+i).style.visibility = 'visible';
                document.getElementById("displayJenisPihakListDiv"+i).style.display = '';
            }else{
                document.getElementById("displayNamaListDiv"+i).style.visibility = 'hidden';
                document.getElementById("displayNamaListDiv"+i).style.display = 'none';
                //div no.pengenalan
                document.getElementById("displayNoListDiv"+i).style.visibility = 'hidden';
                document.getElementById("displayNoListDiv"+i).style.display = 'none';
                //div jenis PB
                document.getElementById("displayJenisListDiv"+i).style.visibility = 'hidden';
                document.getElementById("displayJenisListDiv"+i).style.display = 'none';
                //div syer
                document.getElementById("displaySyerListDiv"+i).style.visibility = 'hidden';
                document.getElementById("displaySyerListDiv"+i).style.display = 'none';
                //div jenis pihak
                document.getElementById("displayJenisPihakListDiv"+i).style.visibility = 'hidden';
                document.getElementById("displayJenisPihakListDiv"+i).style.display = 'none';
            }
            for (var j = 0; j < bilLoop; j++){
                var pilihCheckBox = document.getElementById('pilihCheckBox'+i+j).checked;
                //                    alert("true / false ::: "+pilihCheckBox);
                if( pilihCheckBox == true){
                    bilPilih++;
                }
            }
        }
        
        //        alert(bilDisable);
        if(bilDisable == 0){
            $('#simpanMaklumat').hide();
        }
        
    });
    
    function validate (){
        var statusKaveator = $('#maklumatKaveator').val();
        
        //        if(statusKaveator == false){
        if($('#urusanPilihan').val()=="")
        {
            alert('Sila pilih urusan terlebih dahulu');
            $('#urusan').focus();
            return false;
        }
        
        var bilPilih= 0;
        var bil =  ${fn:length(actionBean.hakmilikPermohonanList)}; //list hakmilik permohonan list
        for (var i = 0; i < bil; i++){
            var bilLoop =  document.getElementById('valLoop'+i).value;
            for (var j = 0; j < bilLoop; j++){
                var pilihCheckBox = document.getElementById('pilihCheckBox'+i+j).checked;
                //                    alert("true / false ::: "+pilihCheckBox);
                if( pilihCheckBox == true){
                    bilPilih++;
                }
            }
        }
        
        //        alert(bilPilih);
        if(bilPilih == 0){
            alert("Sila pilih pihak yang dikehendaki");
            return false;
        }
        //        }
        
        return true;
    }
    
    function doOpen(idHakmilik) {
        var dalamanNilai1 = $('#urusanPilihan').val();
        if($('#urusanPilihan').val()=="")
        {
            alert('Sila pilih urusan terlebih dahulu');
            $('#urusan').focus();
            return false;
        }
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pihak_berkepentingan?showSearchForm&idHakmilik=' + idHakmilik+'&valUrusanPilihan='+dalamanNilai1;
        window.open(url,  "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }
    
    function doOpenKaveat(idHakmilik) {
       
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pihak_berkepentingan?showSearchForm&idHakmilik=' + idHakmilik+'&valUrusanPilihan=KVPT';
        window.open(url,  "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }
    
    function removePemohon(i){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pihak_berkepentingan?deletePemohon&id='+i;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        
    }
    
        
    function removePermohonanPihak(i){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pihak_berkepentingan?deletePermohonanPihak&id='+i;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    
    function selectPihak(i,index) {
        var urusanPilihan = $('#urusanPilihan').val();
        //        alert(i);
        //        alert(urusanPilihan);
        
        if($('#urusanPilihan').val()=="")
        {
            alert('Sila pilih urusan terlebih dahulu');
            $('#urusan').focus();
            document.getElementById("pilihCheckBox"+index).checked = false;
            return false;
        }else{
            if(urusanPilihan == 'TTWKP' || urusanPilihan == 'TTWLM'){
                if(i == 'MEL'){
                    alert("Sila pilih pihak yang bukan beragama Islam sahaja.");
                    document.getElementById("pilihCheckBox"+index).checked = false;
                    return false;
                }
            }else if(urusanPilihan == 'TTWLB'){
                if(i != 'MEL'){
                    alert("Sila pilih pihak yang beragama Islam sahaja.");
                    document.getElementById("pilihCheckBox"+index).checked = false;
                    return false;
                }
            }
        }
    
           
    }
    

</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPihakBerkepentinganActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="maklumatKaveator" id="maklumatKaveator"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${view}">
                <legend>
                    Pihak Berkepentingan
                </legend>
                <p>
                    <label>Urusan :</label>
                    <s:select name="urusan" id="urusanPilihan">
                        <c:forEach items="${actionBean.senaraiKodUrusan}" var="line">
                            <s:option value="${line.kod}">${line.kod} - ${line.nama}</s:option>
                        </c:forEach>
                    </s:select>&nbsp;
                </p> 

                <div class="content" align="center">
                    Senarai Pihak Terlibat <br>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                        <display:column title="Nama Pihak">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.listPihak}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:forEach items="${actionBean.listPemohon}" var="i">
                                        <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${j.pihak.nama}"/><br>
                                            <c:set value="${count + 1}" var="count"/><br>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Bahagian Terlibat">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.listPihak}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:forEach items="${actionBean.listPemohon}" var="i">
                                        <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${i.syerPembilang}/${i.syerPenyebut}"/><br>
                                            <c:set value="${count + 1}" var="count"/><br>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="Urusan">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.listPihak}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:forEach items="${actionBean.listPemohon}" var="i">
                                        <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${i.dalamanNilai1}"/>&nbsp;
                                            <c:set value="${count + 1}" var="count"/>&nbsp;<br><br>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </display:column>
                    </display:table>
                    <br>
                    Senarai Pihak Baru Terlibat <br>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                        <display:column title="Nama Pihak">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${j.pihak.nama}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if> 
                            </c:forEach>
                        </display:column>
                        <display:column title="Bahagian Terlibat">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${j.syerPembilang}/${j.syerPenyebut}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if> 
                            </c:forEach>
                        </display:column>
                        <display:column title="Jenis Pihak">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${j.jenis.nama}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if> 
                            </c:forEach>
                        </display:column>
                        <display:column title="Urusan">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${j.dalamanNilai1}"/>
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanPihak('${j.idPermohonanPihak}');"/>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if> 
                            </c:forEach>
                        </display:column>
                    </display:table>
                </div>
            </c:if>

            <c:if test="${edit}">
                <c:if test="${actionBean.addPemohon eq true}">
                    <legend>
                        Pihak Berkepentingan
                    </legend>
                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                        <p>
                            <label>Urusan :</label>
                            <s:select name="urusan"  style="width:310px;" id="urusanPilihan">
                                <s:option value="">Sila Pilih</s:option>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '351'}">
                                    <s:option value="TTWB">Endorsan Tanah Tanpa Waris</s:option>
                                    <s:option value="TTWLB">Tanah Diletakhak Kepada Baitumal</s:option>
                                    <s:option value="TTWKP">Tanah Dikembalikan kepada PBN</s:option> 
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '352'}">
                                    <s:option value="TTB">Endorsan Tanah Terbiar</s:option>
                                    <s:option value="TTTK">Tanah Terbiar kembali kepada PBN</s:option>
                                </c:if>

                            </s:select>&nbsp;
                        </p> 
                    </c:if>

                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="No. Hakmilik" >
                                ${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}
                            </display:column>
                            <display:column title="Nama">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPihakBerkepentingan}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                        <input type="checkbox" name="pilihCheckBox${line_rowNum-1}${i}" id="pilihCheckBox${line_rowNum-1}${i}" value="${senarai.pihak.idPihak}">
                                        <input type="hidden" name="syerPembilangVal${line_rowNum-1}${i}" id="syerPembilangVal${line_rowNum-1}${i}" value="${senarai.syerPembilang}">
                                        <input type="hidden" name="syerPenyebutVal${line_rowNum-1}${i}" id="syerPenyebutVal${line_rowNum-1}${i}" value="${senarai.syerPenyebut}">
                                        <input type="hidden" name="jenisVal${line_rowNum-1}${i}" id="jenisVal${line_rowNum-1}${i}" value="${senarai.jenis.kod}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                        <c:set value="${count + 1}" var="count"/>
                                        <c:set value="${i + 1}" var="i"/>
                                        <br>   
                                    </c:if>
                                </c:forEach>
                                <input type="hidden" name="valLoop${line_rowNum-1}" id="valLoop${line_rowNum-1}" value="${count-1}">
                                <div id="selectedPenggunaDiv" style="visibility: hidden; display:none ">
                                    <c:set value="1" var="totalCount"/>
                                    <c:set value="0" var="j"/>
                                    <c:forEach items="${actionBean.hakmilikPihakBerkepentinganList}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:set value="${totalCount + 1}" var="totalCount"/>
                                            <c:set value="${j + 1}" var="j"/>
                                            <br>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div id="displayNamaListDiv${line_rowNum-1}" style="visibility: hidden; display:none ">
                                    <c:forEach items="${actionBean.listPihak}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <input type="checkbox" name="" id="" value="" checked disabled>
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br>
                                            <c:set value="${count + 1}" var="count"/>
                                            <c:set value="${i + 1}" var="i"/>
                                            <br>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <input type="hidden" name="valLoopTotal${line_rowNum-1}" id="valLoopTotal${line_rowNum-1}" value="${totalCount-1}">
                            </display:column>
                            <display:column title="No.Pengenalan">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.listPihakBerkepentingan}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:if test="${not empty senarai.pihak.noPengenalan}">
                                            <c:out value="${senarai.pihak.noPengenalan}"/>&nbsp;&nbsp;<br>
                                        </c:if>
                                        <c:if test="${empty senarai.pihak.noPengenalan}">
                                            Tiada
                                        </c:if>
                                        <c:set value="${count + 1}" var="count"/>
                                        <br>   
                                    </c:if>
                                </c:forEach>

                                <div id="displayNoListDiv${line_rowNum-1}" style="visibility: hidden; display:none ">
                                    <c:forEach items="${actionBean.listPihak}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:if test="${not empty senarai.pihak.noPengenalan}">
                                                <c:out value="${senarai.pihak.noPengenalan}"/>&nbsp;&nbsp;<br>
                                            </c:if>
                                            <c:if test="${empty senarai.pihak.noPengenalan}">
                                                Tiada
                                            </c:if>
                                            <c:set value="${count + 1}" var="count"/>
                                            <c:set value="${i + 1}" var="i"/>
                                            <br>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </display:column>
                            <display:column title="Jenis Pihak Berkepentingan">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.listPihakBerkepentingan}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.jenis.nama}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:forEach>

                                <div id="displayJenisListDiv${line_rowNum-1}" style="visibility: hidden; display:none ">
                                    <c:forEach items="${actionBean.listPihak}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${senarai.jenis.nama}"/><br>
                                            <c:set value="${count + 1}" var="count"/>
                                            <c:set value="${i + 1}" var="i"/>
                                            <br>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </display:column>
                            <display:column title="Syer yang dimiliki">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.listPihakBerkepentingan}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:forEach>
                                <div id="displaySyerListDiv${line_rowNum-1}" style="visibility: hidden; display:none ">
                                    <c:forEach items="${actionBean.listPihak}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                                            <c:set value="${count + 1}" var="count"/>
                                            <c:set value="${i + 1}" var="i"/>
                                            <br>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </display:column>
                            <display:column title="Jenis Pihak">
                                <c:set value="1" var="count"/>
                                <c:forEach items="${actionBean.listPihakBerkepentingan}" var="senarai">
                                    <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${senarai.jenis.nama}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if>
                                </c:forEach>
                                <div id="displayJenisPihakListDiv${line_rowNum-1}" style="visibility: hidden; display:none ">
                                    <c:forEach items="${actionBean.listPihak}" var="senarai">
                                        <c:if test="${line.hakmilik.idHakmilik eq senarai.hakmilik.idHakmilik}">
                                            <c:out value="${count}"/>)&nbsp;
                                            <c:out value="${senarai.jenis.nama}"/><br>
                                            <c:set value="${count + 1}" var="count"/>
                                            <c:set value="${i + 1}" var="i"/>
                                            <br>   
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </display:column>
                        </display:table>

                        <c:if test="${fn:length(actionBean.hakmilikPermohonanList) ne 0}">
                            <p align="center">
                                <s:button class="btn" value="Simpan" name="simpanPemohon" id="simpanMaklumat" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                            </p>
                        </c:if>

                        <br>

                        <br>
                        Senarai Pihak Terlibat <br>
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                            <display:column title="Nama Pihak">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPihak}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:forEach items="${actionBean.listPemohon}" var="i">
                                            <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${j.pihak.nama}"/><br>
                                                <c:set value="${count + 1}" var="count"/><br>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Bahagian Terlibat">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPihak}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:forEach items="${actionBean.listPemohon}" var="i">
                                            <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${i.syerPembilang}/${i.syerPenyebut}"/><br>
                                                <c:set value="${count + 1}" var="count"/><br>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Urusan">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPihak}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:forEach items="${actionBean.listPemohon}" var="i">
                                            <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${i.dalamanNilai1}"/>&nbsp;
                                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePemohon('${i.idPemohon}');"/>
                                                <c:set value="${count + 1}" var="count"/><br><br>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                        </display:table>
                    </div>
                </c:if>

                <c:if test="${actionBean.addPermohonanPihak eq true}">
                    <p>
                        <label>Urusan :</label>
                        <s:select name="urusan" id="urusanPilihan">
                            <c:forEach items="${actionBean.senaraiKodUrusan}" var="line">
                                <s:option value="${line.kod}">${line.kod} - ${line.nama}</s:option>
                            </c:forEach>
                        </s:select>&nbsp;
                    </p> 
                    <div class="content" align="center">
                        Senarai Pihak Terlibat <br>
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                            <display:column title="Nama Pihak">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPihak}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:forEach items="${actionBean.listPemohon}" var="i">
                                            <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${j.pihak.nama}"/><br>
                                                <c:set value="${count + 1}" var="count"/><br>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Bahagian Terlibat">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPihak}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:forEach items="${actionBean.listPemohon}" var="i">
                                            <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${i.syerPembilang}/${i.syerPenyebut}"/><br>
                                                <c:set value="${count + 1}" var="count"/><br>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                            <display:column title="Urusan">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.listPihak}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:forEach items="${actionBean.listPemohon}" var="i">
                                            <c:if test="${i.pihak.idPihak eq j.pihak.idPihak}">
                                                <c:out value="${count}"/>)&nbsp;
                                                <c:out value="${i.dalamanNilai1}"/>&nbsp;
                                                <c:set value="${count + 1}" var="count"/>&nbsp;<br><br>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                        </display:table>
                        <br>
                        Senarai Pihak Baru Terlibat <br>
                        <c:if test="${actionBean.carianPermohonanPihak eq true}">
                            <span class=instr><em>Sila klik butang tambah <img alt='Tambah Pihak' id='addCaw' src='${pageContext.request.contextPath}/images/tambah.png'  /> untuk menambah pihak baru terlibat.</em></span> 
                            <br/>
                        </c:if>
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                            <display:column title="Nama Pihak">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${j.pihak.nama}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if> 
                                </c:forEach>
                            </display:column>
                            <display:column title="Bahagian Terlibat">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${j.syerPembilang}/${j.syerPenyebut}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if> 
                                </c:forEach>
                            </display:column>
                            <display:column title="Jenis Pihak">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${j.jenis.nama}"/><br>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if> 
                                </c:forEach>
                            </display:column>
                            <display:column title="Urusan">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${j.dalamanNilai1}"/>
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanPihak('${j.idPermohonanPihak}');"/>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if> 
                                </c:forEach>
                            </display:column>
                            <c:if test="${actionBean.carianPermohonanPihak eq true}">
                                <display:column title="Tambah">
                                    <div align="center">
                                        <img alt='Tambah Pihak' id='addCaw' src='${pageContext.request.contextPath}/images/tambah.png' onmouseover="this.style.cursor='pointer';" onclick="doOpen('${line.hakmilik.idHakmilik}');"/>
                                    </div>
                                </display:column>
                            </c:if>


                        </display:table>
                    </div>
                </c:if>

                <c:if test="${actionBean.maklumatKaveator eq true}">
                    <legend>
                        Maklumat Kaveator
                    </legend>

                    Senarai Pihak Baru Terlibat <br>

                    <span class=instr><em>Sila klik butang tambah untuk menambah pihak baru terlibat.</em></span><br/>
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                        <display:column title="Nama Pihak">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${j.pihak.nama}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if> 
                            </c:forEach>
                        </display:column>
                        <display:column title="Bahagian Terlibat">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${j.syerPembilang}/${j.syerPenyebut}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if> 
                            </c:forEach>
                        </display:column>
                        <display:column title="Jenis Pihak">
                            <c:set value="1" var="count"/>
                            <c:set value="0" var="i"/>
                            <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                    <c:out value="${count}"/>)&nbsp;
                                    <c:out value="${j.jenis.nama}"/><br>
                                    <c:set value="${count + 1}" var="count"/><br>
                                </c:if> 
                            </c:forEach>
                        </display:column>
                        <c:if test="${actionBean.maklumatKaveator eq false}">
                            <display:column title="Urusan">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <c:out value="${count}"/>)&nbsp;
                                        <c:out value="${j.dalamanNilai1}"/>
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanPihak('${j.idPermohonanPihak}');"/>
                                        <c:set value="${count + 1}" var="count"/><br>
                                    </c:if> 
                                </c:forEach>
                            </display:column>
                            <display:column title="Tambah">
                                <div align="center">
                                    <img alt='Tambah Pihak' id='addCaw' src='${pageContext.request.contextPath}/images/tambah.png' onmouseover="this.style.cursor='pointer';" onclick="doOpen('${line.hakmilik.idHakmilik}');"/>
                                </div>
                            </display:column>
                        </c:if>
                        <c:if test="${actionBean.maklumatKaveator eq true}">
                            <display:column title="Hapus">
                                <c:set value="1" var="count"/>
                                <c:set value="0" var="i"/>
                                <c:forEach items="${actionBean.permohonanPihakList}" var="j">
                                    <c:if test="${line.hakmilik.idHakmilik eq j.hakmilik.idHakmilik}">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removePermohonanPihak('${j.idPermohonanPihak}');"/>
                                        <c:set value="${count + 1}" var="count"/><br><br>
                                    </c:if> 
                                </c:forEach>
                            </display:column>
                        </c:if>
                    </display:table>
                </c:if>





            </c:if>

        </fieldset>
    </div>
</s:form>